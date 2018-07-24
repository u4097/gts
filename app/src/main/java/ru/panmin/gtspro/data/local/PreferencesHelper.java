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

}