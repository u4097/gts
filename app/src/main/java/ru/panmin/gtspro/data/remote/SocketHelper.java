package ru.panmin.gtspro.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.panmin.gtspro.data.local.PreferencesHelper;
import ru.panmin.gtspro.data.models.wsrequests.BaseWsRequest;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.TextUtils;
import tech.gusavila92.websocketclient.WebSocketClient;
import timber.log.Timber;

@Singleton
public class SocketHelper {

    private final PreferencesHelper preferencesHelper;
    @Nullable private WebSocketClient webSocketClient = null;

    private boolean isConnected = false;

    @Inject
    public SocketHelper(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
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

    public <T extends BaseWsRequest> void create(boolean createWithConnect, @Nullable T request) {
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
                        }

                        @Override
                        public void onBinaryReceived(byte[] data) {
                        }

                        @Override
                        public void onPingReceived(byte[] data) {
                        }

                        @Override
                        public void onPongReceived(byte[] data) {
                        }

                        @Override
                        public void onException(Exception e) {
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
            webSocketClient.send(request.toJsonString());
        } else {
            createAndSendRequest(request);
        }
    }

}