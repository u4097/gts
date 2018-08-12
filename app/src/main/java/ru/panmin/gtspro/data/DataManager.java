package ru.panmin.gtspro.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.realm.RealmList;
import ru.panmin.gtspro.data.local.PreferencesHelper;
import ru.panmin.gtspro.data.local.RealmHelper;
import ru.panmin.gtspro.data.models.AnswerToQuestion;
import ru.panmin.gtspro.data.models.HotLine;
import ru.panmin.gtspro.data.models.Merchandiser;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.data.models.requests.AuthRequest;
import ru.panmin.gtspro.data.models.responses.AddressProgramResponse;
import ru.panmin.gtspro.data.models.responses.AuthResponse;
import ru.panmin.gtspro.data.models.responses.UserInfoResponse;
import ru.panmin.gtspro.data.models.wsrequests.AddressProgramWsRequest;
import ru.panmin.gtspro.data.models.wsrequests.BaseWsRequest;
import ru.panmin.gtspro.data.models.wsrequests.FormWsRequest;
import ru.panmin.gtspro.data.models.wsrequests.UserInfoWsRequest;
import ru.panmin.gtspro.data.remote.ApiService;
import ru.panmin.gtspro.data.remote.SocketHelper;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.RxEventBus;
import ru.panmin.gtspro.utils.TextUtils;

@Singleton
public class DataManager {

    private static final int UPDATE_HOUR = 8;

    private final PreferencesHelper preferencesHelper;
    private final ApiService apiService;
    private final RxEventBus rxEventBus;
    private final RealmHelper realmHelper;
    private final SocketHelper socketHelper;

    @Inject
    DataManager(
            PreferencesHelper preferencesHelper,
            ApiService apiService,
            RxEventBus rxEventBus,
            RealmHelper realmHelper,
            SocketHelper socketHelper
    ) {
        this.preferencesHelper = preferencesHelper;
        this.apiService = apiService;
        this.rxEventBus = rxEventBus;
        this.realmHelper = realmHelper;
        this.socketHelper = socketHelper;
    }

    public void clear() {
        close();
        clearPreferences();
        clearDataBase();
    }


    /* SHARED PREFERENCES */
    public void clearPreferences() {
        preferencesHelper.clear();
    }

    public boolean isWasFirstPermissionDialog(List<String> permissions) {
        return preferencesHelper.isWasFirstPermissionDialog(permissions);
    }

    public void setWasPermissionDialog(List<String> permissions) {
        preferencesHelper.setWasPermissionDialog(permissions);
    }

    public String getLanguage() {
        return PreferencesHelper.getLanguage();
    }

    public void setLanguage(String language) {
        PreferencesHelper.setLanguage(language);
    }

    public String getSortType() {
        return preferencesHelper.getSortType();
    }

    public void setSortType(String sortType) {
        preferencesHelper.setSortType(sortType);
    }

    public String getToken() {
        return preferencesHelper.getToken();
    }

    public void setToken(String token) {
        preferencesHelper.setToken(token);
    }

    public String getId() {
        return preferencesHelper.getId();
    }

    public void setId(String id) {
        preferencesHelper.setId(id);
    }

    public String getUserName() {
        return preferencesHelper.getUserName();
    }

    public void setUserName(String userName) {
        preferencesHelper.setUserName(userName);
    }

    public String getRole() {
        return preferencesHelper.getRole();
    }

    public void setRole(String role) {
        preferencesHelper.setRole(role);
    }

    public String getSupervisorId() {
        return preferencesHelper.getSupervisorId();
    }

    public void setSupervisorId(String supervisorId) {
        preferencesHelper.setSupervisorId(supervisorId);
    }

    public String getFullNameRu() {
        return preferencesHelper.getFullNameRu();
    }

    public void setFullNameRu(String fullNameRu) {
        preferencesHelper.setFullNameRu(fullNameRu);
    }

    public String getFullNameEn() {
        return preferencesHelper.getFullNameEn();
    }

    public void setFullNameEn(String fullNameEn) {
        preferencesHelper.setFullNameEn(fullNameEn);
    }

    public Calendar getSyncTime() {
        return preferencesHelper.getSyncTime();
    }

    public void setSyncTime(Calendar calendar) {
        preferencesHelper.setSyncTime(calendar);
    }

    public int getAutoCheckoutTime() {
        return preferencesHelper.getAutoCheckoutTime();
    }

    public void setAutoCheckoutTime(int autoCheckoutTime) {
        preferencesHelper.setAutoCheckoutTime(autoCheckoutTime);
    }

    public int getTradePointRadius() {
        return preferencesHelper.getTradePointRadius();
    }

    public void setTradePointRadius(int tradePointRadius) {
        preferencesHelper.setTradePointRadius(tradePointRadius);
    }

    public boolean isAuth() {
        return !TextUtils.isEmpty(getToken());
    }

    public String getFullName() {
        switch (getLanguage()) {
            case Constants.LANGUAGE_RUSSIAN:
                return TextUtils.isEmpty(getFullNameRu()) ? TextUtils.isEmpty(getFullNameEn()) ? "" : getFullNameEn() : getFullNameRu();
            case Constants.LANGUAGE_ENGLISH:
                return TextUtils.isEmpty(getFullNameEn()) ? TextUtils.isEmpty(getFullNameRu()) ? "" : getFullNameRu() : getFullNameEn();
            default:
                return "";
        }
    }

    public boolean isMerchandiser() {
        return TextUtils.equals(Constants.ROLE_MERCHANDISER, getRole());
    }

    public boolean isSupervisor() {
        return TextUtils.equals(Constants.ROLE_SUPERVISOR, getRole());
    }

    public boolean isNeedUpdateDB() {
        Calendar lastSync = getSyncTime();

        Calendar nowCalendar = Calendar.getInstance();

        Calendar yesterdayCalendar = Calendar.getInstance();
        yesterdayCalendar.add(Calendar.DAY_OF_MONTH, -1);

        if (nowCalendar.get(Calendar.YEAR) == lastSync.get(Calendar.YEAR)
                && nowCalendar.get(Calendar.MONTH) == lastSync.get(Calendar.MONTH)
                && nowCalendar.get(Calendar.DAY_OF_MONTH) == lastSync.get(Calendar.DAY_OF_MONTH)) {
            if (lastSync.get(Calendar.HOUR_OF_DAY) < UPDATE_HOUR && nowCalendar.get(Calendar.HOUR_OF_DAY) >= UPDATE_HOUR) {
                return true;
            } else {
                return false;
            }
        } else if (nowCalendar.get(Calendar.YEAR) == lastSync.get(Calendar.YEAR)
                && nowCalendar.get(Calendar.MONTH) == lastSync.get(Calendar.MONTH)
                && nowCalendar.get(Calendar.DAY_OF_MONTH) == lastSync.get(Calendar.DAY_OF_MONTH)) {
            if (nowCalendar.get(Calendar.HOUR_OF_DAY) >= UPDATE_HOUR) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }


    /* API */
    public Single<AuthResponse> auth(String userName, String password) {
        return apiService.auth(new AuthRequest(userName, password))
                .map(authResponse -> {
                    setToken(authResponse.getToken());
                    setId(authResponse.getId());
                    setUserName(authResponse.getUsername());
                    setRole(authResponse.getRole());
                    switch (authResponse.getRole()) {
                        case Constants.ROLE_SUPERVISOR:
                            setSupervisorId(authResponse.getSupervisorId());
                            break;
                    }
                    setFullNameRu(authResponse.getFullName().getRu());
                    setFullNameEn(authResponse.getFullName().getEn());
                    return authResponse;
                });
    }

    public Single<UserInfoResponse> userInfo() {
        return apiService.userInfo()
                .map(userInfoResponse -> {
                    setId(userInfoResponse.getId());
                    setUserName(userInfoResponse.getUsername());
                    setRole(userInfoResponse.getRole());
                    switch (userInfoResponse.getRole()) {
                        case Constants.ROLE_SUPERVISOR:
                            setSupervisorId(userInfoResponse.getSupervisorId());
                            break;
                    }
                    setFullNameRu(userInfoResponse.getFullName().getRu());
                    setFullNameEn(userInfoResponse.getFullName().getEn());
                    return userInfoResponse;
                });
    }

    public Single<AddressProgramResponse> addressProgram() {
        return apiService.addressProgram()
                .map(addressProgramResponse -> {
                    setAutoCheckoutTime(addressProgramResponse.getAutoCheckoutTime());
                    setTradePointRadius(addressProgramResponse.getTradePointRadius());
                    return addressProgramResponse;
                });
    }

    public Single<AddressProgramResponse> addressProgramWithoutSku() {
        return apiService.addressProgramWithoutSku()
                .map(addressProgramResponse -> {
                    setAutoCheckoutTime(addressProgramResponse.getAutoCheckoutTime());
                    setTradePointRadius(addressProgramResponse.getTradePointRadius());
                    return addressProgramResponse;
                });
    }


    /* DATABASE */
    public void clearDataBase() {
        realmHelper.clear();
    }

    public List<TradePoint> getTradePoints() {
        return realmHelper.getTradePoints();
    }

    public void setTradePoints(RealmList<TradePoint> tradePoints) {
        realmHelper.setTradePoints(tradePoints);
    }

    @Nullable
    public TradePoint getTradePointById(String id) {
        return realmHelper.getTradePointById(id);
    }

    @Nullable
    public Merchandiser getMerchandiserByName(String name) {
        return realmHelper.getMerchandiserByName(name);
    }

    @Nullable
    public HotLine getHotLine() {
        return realmHelper.getHotLine();
    }

    public void setHotLine(HotLine hotLine) {
        realmHelper.setHotLine(hotLine);
    }

    @Nullable
    public Promo getPromoById(String id) {
        return realmHelper.getPromoById(id);
    }


    /* SOCKET */
    public void createWithConnect() {
        socketHelper.createWithConnect();
    }

    public void createWithoutConnect() {
        socketHelper.createWithConnect();
    }

    public <T extends BaseWsRequest> void createAndSendRequest(@NonNull T request) {
        socketHelper.createAndSendRequest(request);
    }

    public void connect() {
        socketHelper.connect();
    }

    public void close() {
        socketHelper.close();
    }

    public boolean isConnected() {
        return socketHelper.isConnected();
    }

    public void wsUserInfo() {
        socketHelper.send(new UserInfoWsRequest(getId()));
    }

    public void wsAddressProgram() {
        socketHelper.send(new AddressProgramWsRequest(getId()));
    }

    public void wsForm(List<AnswerToQuestion> data) {
        socketHelper.send(new FormWsRequest(getId(), data));
    }

}