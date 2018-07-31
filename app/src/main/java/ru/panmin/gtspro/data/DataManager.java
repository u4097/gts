package ru.panmin.gtspro.data;

import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import ru.panmin.gtspro.data.local.PreferencesHelper;
import ru.panmin.gtspro.data.models.requests.AuthRequest;
import ru.panmin.gtspro.data.models.responses.AddressProgramResponse;
import ru.panmin.gtspro.data.models.responses.AuthResponse;
import ru.panmin.gtspro.data.models.responses.UserInfoResponse;
import ru.panmin.gtspro.data.remote.ApiService;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.RxEventBus;

@Singleton
public class DataManager {

    private final PreferencesHelper preferencesHelper;
    private final ApiService apiService;
    private final RxEventBus rxEventBus;

    @Inject
    DataManager(PreferencesHelper preferencesHelper, ApiService apiService, RxEventBus rxEventBus) {
        this.preferencesHelper = preferencesHelper;
        this.apiService = apiService;
        this.rxEventBus = rxEventBus;
    }


    /* SHARED PREFERENCES */
    public void clear() {
        preferencesHelper.clear();
    }

    public boolean isWasFirstPermissionDialog(List<String> permissions) {
        return preferencesHelper.isWasFirstPermissionDialog(permissions);
    }

    public void setWasPermissionDialog(List<String> permissions) {
        preferencesHelper.setWasPermissionDialog(permissions);
    }

    public String getLanguage() {
        return preferencesHelper.getLanguage();
    }

    public void setLanguage(String language) {
        preferencesHelper.setLanguage(language);
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

    public String getSuoervisorId() {
        return preferencesHelper.getSuoervisorId();
    }

    public void setSuoervisorId(String suoervisorId) {
        preferencesHelper.setSuoervisorId(suoervisorId);
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

    public boolean isAuth() {
        return !TextUtils.isEmpty(getToken());
    }

    public String getFullName() {
        switch (getLanguage()) {
            case Constants.LANGUAGE_RUSSIAN:
                return getFullNameRu();
            case Constants.LANGUAGE_ENGLISH:
                return getFullNameEn();
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

    /* API */
    public Single<AuthResponse> auth(String userName, String password) {
        return apiService.auth(new AuthRequest(userName, password));
    }

    public Single<UserInfoResponse> userInfo() {
        return apiService.userInfo();
    }

    public Single<AddressProgramResponse> addressProgram() {
        return apiService.addressProgram();
    }

}