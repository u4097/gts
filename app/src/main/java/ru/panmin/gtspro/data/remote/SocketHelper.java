package ru.panmin.gtspro.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.panmin.gtspro.data.local.PreferencesHelper;
import ru.panmin.gtspro.data.local.RealmHelper;
import ru.panmin.gtspro.data.models.wsrequests.BaseWsRequest;
import ru.panmin.gtspro.data.models.wsresponses.AddressProgramWsResponse;
import ru.panmin.gtspro.data.models.wsresponses.BaseWsResponse;
import ru.panmin.gtspro.data.models.wsresponses.ClaimAnsweredWsResponse;
import ru.panmin.gtspro.data.models.wsresponses.ClaimWsResponse;
import ru.panmin.gtspro.data.models.wsresponses.ErrorWsResponse;
import ru.panmin.gtspro.data.models.wsresponses.FormWsResponse;
import ru.panmin.gtspro.data.models.wsresponses.MerchandiserVisitsSwResponse;
import ru.panmin.gtspro.data.models.wsresponses.UserInfoWsResponse;
import ru.panmin.gtspro.data.models.wsresponses.VisitAcceptedWsResponse;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.GsonUtils;
import ru.panmin.gtspro.utils.RxEventBus;
import ru.panmin.gtspro.utils.TextUtils;
import tech.gusavila92.websocketclient.WebSocketClient;
import timber.log.Timber;

@Singleton
public class SocketHelper {

    private final PreferencesHelper preferencesHelper;
    private final RealmHelper realmHelper;
    private final RxEventBus rxEventBus;
    private final Gson gson;

    @Nullable private WebSocketClient webSocketClient = null;

    private boolean isConnected = false;

    @Inject
    public SocketHelper(PreferencesHelper preferencesHelper, RealmHelper realmHelper, RxEventBus rxEventBus) {
        this.preferencesHelper = preferencesHelper;
        this.realmHelper = realmHelper;
        this.rxEventBus = rxEventBus;
        gson = GsonUtils.getGson();
    }

    public void createWithConnect() {
        create(true, null);
    }

    public void createWithoutConnect() {
        create(false, null);
    }

    public <T extends BaseWsRequest> void createAndSendRequest(@NonNull T request) {
        create(false, request);
    }

    private <T extends BaseWsRequest> void create(boolean createWithConnect, @Nullable T request) {
        String token = preferencesHelper.getToken();
        if (!TextUtils.isEmpty(token)) {
            try {
                if (webSocketClient == null) {
                    webSocketClient = new WebSocketClient(new URI(Constants.URL_WEB_SOCKET)) {
                        @Override
                        public void onOpen() {
                            isConnected = true;
                            if (request != null) {
                                send(request.toJsonString());
                            }
                        }

                        @Override
                        public void onTextReceived(String message) {

                            switch (gson.fromJson(message, BaseWsResponse.class).getType()) {
                                case Constants.WS_TYPE_USER_INFO:
                                    UserInfoWsResponse userInfoWsResponse = gson.fromJson(message, UserInfoWsResponse.class);
                                    PreferencesHelper.setId(userInfoWsResponse.getData().getId());
                                    preferencesHelper.setUserName(userInfoWsResponse.getData().getUsername());
                                    preferencesHelper.setRole(userInfoWsResponse.getData().getRole());
                                    switch (userInfoWsResponse.getData().getRole()) {
                                        case Constants.ROLE_SUPERVISOR:
                                            preferencesHelper.setSupervisorId(userInfoWsResponse.getData().getSupervisorId());
                                            break;
                                    }
                                    rxEventBus.post(userInfoWsResponse);
                                    break;
                                case Constants.WS_TYPE_ADDRESS_PROGRAM:
                                    AddressProgramWsResponse addressProgramWsResponse
                                            = gson.fromJson(message, AddressProgramWsResponse.class);
                                    preferencesHelper.setAutoCheckoutTime(
                                            addressProgramWsResponse.getData().getAutoCheckoutTime()
                                    );
                                    preferencesHelper.setTradePointRadius(
                                            addressProgramWsResponse.getData().getTradePointRadius()
                                    );
                                    realmHelper.setHotLine(addressProgramWsResponse.getData().getHotLine());
                                    realmHelper.setTradePoints(addressProgramWsResponse.getData().getTradePoints());
                                    rxEventBus.post(addressProgramWsResponse);
                                    break;
                                case Constants.WS_TYPE_FORM:
                                    FormWsResponse formWsResponse = gson.fromJson(message, FormWsResponse.class);
                                    rxEventBus.post(formWsResponse);
                                    break;
                                case Constants.WS_TYPE_CLAIM:
                                    ClaimWsResponse claimWsResponse = gson.fromJson(message, ClaimWsResponse.class);
                                    rxEventBus.post(claimWsResponse);
                                    break;
                                case Constants.WS_TYPE_CLAIM_ANSWERED:
                                    ClaimAnsweredWsResponse claimAnsweredWsResponse
                                            = gson.fromJson(message, ClaimAnsweredWsResponse.class);
                                    rxEventBus.post(claimAnsweredWsResponse);
                                    break;
                                case Constants.WS_TYPE_VISIT_ACCEPTED:
                                    VisitAcceptedWsResponse visitAcceptedWsResponse
                                            = gson.fromJson(message, VisitAcceptedWsResponse.class);
                                    rxEventBus.post(visitAcceptedWsResponse);
                                    break;
                                case Constants.WS_TYPE_MERCHANDISER_VISITS:
                                    MerchandiserVisitsSwResponse merchandiserVisitsSwResponse
                                            = gson.fromJson(message, MerchandiserVisitsSwResponse.class);
                                    rxEventBus.post(merchandiserVisitsSwResponse);
                                    break;
                                case Constants.WS_TYPE_ERROR:
                                    ErrorWsResponse errorWsResponse = gson.fromJson(message, ErrorWsResponse.class);
                                    rxEventBus.post(errorWsResponse);
                                    break;
                            }
                        }

                        @Override
                        public void onBinaryReceived(byte[] data) {
                            Timber.d(Arrays.toString(data));
                        }

                        @Override
                        public void onPingReceived(byte[] data) {
                            Timber.d(Arrays.toString(data));
                        }

                        @Override
                        public void onPongReceived(byte[] data) {
                            Timber.d(Arrays.toString(data));
                        }

                        @Override
                        public void onException(Exception e) {
                            webSocketClient = null;
                            isConnected = false;
                            createWithConnect();
                        }

                        @Override
                        public void onCloseReceived() {
                            isConnected = false;
                            webSocketClient = null;
                        }
                    };
                    webSocketClient.setConnectTimeout(10 * Constants.MINUTE);
                    webSocketClient.setReadTimeout(30 * Constants.MINUTE);
                    webSocketClient.addHeader(
                            Constants.HEADER_AUTHORIZATION,
                            String.format("%s %s", Constants.TOKEN_TYPE_BEARER, token)
                    );
                }
                if (createWithConnect) {
                    connect();
                }
            } catch (URISyntaxException e) {
                Timber.d(e);
            }
        }
    }

    public void connect() {
        if (webSocketClient != null) {
            webSocketClient.connect();
        } else {
            createWithConnect();
        }
    }

    public void close() {
        if (webSocketClient != null) {
            webSocketClient.close();
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    public <T extends BaseWsRequest> void send(T request) {
        if (webSocketClient != null) {
            if (isConnected()) {
                webSocketClient.send(request.toJsonString());
            } else {
                close();
                createAndSendRequest(request);
            }
        } else {
            createAndSendRequest(request);
        }
    }

}