package ru.panmin.gtspro.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.panmin.gtspro.injection.ApplicationContext;

@Singleton
public class PreferencesHelper {

    private static final String PREF_FILE_NAME = "ru.panmin.gtspro.shared.preferences";

    private static final String PREF_WAS_PERMISSION_DIALOGS = "ru.panmin.gtspro.was.permission.dialogs";

    private static final String PREF_TOKEN = "token";
    private static final String DEFAULT_PREF_TOKEN = "";

    private static final String PREF_ID = "token";
    private static final String DEFAULT_PREF_ID = "";

    private static final String PREF_USERNAME = "username";
    private static final String DEFAULT_PREF_USERNAME = "";

    private static final String PREF_ROLE = "role";
    private static final String DEFAULT_PREF_ROLE = "";

    private static final String PREF_FULL_NAME_RU = "full.name.ru";
    private static final String DEFAULT_PREF_FULL_NAME_RU = "";

    private static final String PREF_FULL_NAME_EN = "full.name.en";
    private static final String DEFAULT_PREF_FULL_NAME_EN = "";

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

}