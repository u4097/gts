package ru.panmin.gtspro.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.panmin.gtspro.injection.ApplicationContext;
import ru.panmin.gtspro.utils.Constants;

@Singleton
public class PreferencesHelper {

    private static final String PREF_FILE_NAME = "ru.panmin.gtspro.shared.preferences";

    private static final String PREF_WAS_PERMISSION_DIALOGS = "ru.panmin.gtspro.was.permission.dialogs";

    private static final String PREF_LANGUAGE = "language";
    private static final String DEFAULT_PREF_LANGUAGE = Constants.LANGUAGE_RUSSIAN;

    private static final String PREF_SORT_TYPE = "sort.type";
    private static final String DEFAULT_PREF_SORT_TYPE = Constants.SORT_TYPE_ALPHABET;

    private static final String PREF_TOKEN = "token";
    private static final String DEFAULT_PREF_TOKEN = "";

    private static final String PREF_ID = "id";
    private static final String DEFAULT_PREF_ID = "";

    private static final String PREF_USERNAME = "username";
    private static final String DEFAULT_PREF_USERNAME = "";

    private static final String PREF_ROLE = "role";
    private static final String DEFAULT_PREF_ROLE = "";

    private static final String PREF_SUPERVISOR_ID = "supervisor.id";
    private static final String DEFAULT_PREF_SUPERVISOR_ID = "";

    private static final String PREF_FULL_NAME_RU = "full.name.ru";
    private static final String DEFAULT_PREF_FULL_NAME_RU = "";

    private static final String PREF_FULL_NAME_EN = "full.name.en";
    private static final String DEFAULT_PREF_FULL_NAME_EN = "";

    private static final String PREF_SYNC_TIME = "sync.time";
    private static final long DEFAULT_PREF_SYNC_TIME = 0;

    private static final String PREF_AUTO_CHECKOUT_TIME = "auto.checkout.time";
    private static final int DEFAULT_PREF_AUTO_CHECKOUT_TIME = 5;

    private static final String PREF_TRADE_POINT_RADIUS = "trade.point.radius";
    private static final int DEFAULT_PREF_TRADE_POINT_RADIUS = 500;

    private final SharedPreferences preferencesHelper;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        preferencesHelper = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        preferencesHelper.edit().clear().apply();
    }

    public boolean isWasFirstPermissionDialog(List<String> permissions) {
        Set<String> wasPermissionDialogs = preferencesHelper.getStringSet(PREF_WAS_PERMISSION_DIALOGS, new HashSet<>());
        boolean isContains = false;
        for (String permission : permissions) {
            if (wasPermissionDialogs.contains(permission)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    public void setWasPermissionDialog(List<String> permissions) {
        Set<String> wasPermissionDialogs = preferencesHelper.getStringSet(PREF_WAS_PERMISSION_DIALOGS, new HashSet<>());
        wasPermissionDialogs.addAll(permissions);
        preferencesHelper.edit().putStringSet(PREF_WAS_PERMISSION_DIALOGS, wasPermissionDialogs).apply();
    }

    public String getLanguage() {
        return preferencesHelper.getString(PREF_LANGUAGE, DEFAULT_PREF_LANGUAGE);
    }

    public void setLanguage(String language) {
        preferencesHelper.edit().putString(PREF_LANGUAGE, language).apply();
    }

    public String getSortType() {
        return preferencesHelper.getString(PREF_SORT_TYPE, DEFAULT_PREF_SORT_TYPE);
    }

    public void setSortType(String sortType) {
        preferencesHelper.edit().putString(PREF_SORT_TYPE, sortType).apply();
    }

    public String getToken() {
        return preferencesHelper.getString(PREF_TOKEN, DEFAULT_PREF_TOKEN);
    }

    public void setToken(String token) {
        preferencesHelper.edit().putString(PREF_TOKEN, token).apply();
    }

    public String getId() {
        return preferencesHelper.getString(PREF_ID, DEFAULT_PREF_ID);
    }

    public void setId(String id) {
        preferencesHelper.edit().putString(PREF_ID, id).apply();
    }

    public String getUserName() {
        return preferencesHelper.getString(PREF_USERNAME, DEFAULT_PREF_USERNAME);
    }

    public void setUserName(String userName) {
        preferencesHelper.edit().putString(PREF_USERNAME, userName).apply();
    }

    public String getRole() {
        return preferencesHelper.getString(PREF_ROLE, DEFAULT_PREF_ROLE);
    }

    public void setRole(String role) {
        preferencesHelper.edit().putString(PREF_ROLE, role).apply();
    }

    public String getSupervisorId() {
        return preferencesHelper.getString(PREF_SUPERVISOR_ID, DEFAULT_PREF_SUPERVISOR_ID);
    }

    public void setSupervisorId(String supervisorId) {
        preferencesHelper.edit().putString(PREF_SUPERVISOR_ID, supervisorId).apply();
    }

    public String getFullNameRu() {
        return preferencesHelper.getString(PREF_FULL_NAME_RU, DEFAULT_PREF_FULL_NAME_RU);
    }

    public void setFullNameRu(String fullNameRu) {
        preferencesHelper.edit().putString(PREF_FULL_NAME_RU, fullNameRu).apply();
    }

    public String getFullNameEn() {
        return preferencesHelper.getString(PREF_FULL_NAME_EN, DEFAULT_PREF_FULL_NAME_EN);
    }

    public void setFullNameEn(String fullNameEn) {
        preferencesHelper.edit().putString(PREF_FULL_NAME_EN, fullNameEn).apply();
    }

    public Calendar getSyncTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(preferencesHelper.getLong(PREF_SYNC_TIME, DEFAULT_PREF_SYNC_TIME)));
        return calendar;
    }

    public void setSyncTime(Calendar calendar) {
        preferencesHelper.edit().putLong(PREF_SYNC_TIME, calendar.getTime().getTime()).apply();
    }

    public int getAutoCheckoutTime() {
        return preferencesHelper.getInt(PREF_AUTO_CHECKOUT_TIME, DEFAULT_PREF_AUTO_CHECKOUT_TIME);
    }

    public void setAutoCheckoutTime(int autoCheckoutTime) {
        preferencesHelper.edit().putInt(PREF_AUTO_CHECKOUT_TIME, autoCheckoutTime).apply();
    }

    public int getTradePointRadius() {
        return preferencesHelper.getInt(PREF_TRADE_POINT_RADIUS, DEFAULT_PREF_TRADE_POINT_RADIUS);
    }

    public void setTradePointRadius(int tradePointRadius) {
        preferencesHelper.edit().putInt(PREF_TRADE_POINT_RADIUS, tradePointRadius).apply();
    }

}