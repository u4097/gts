package ru.panmin.gtspro.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

import ru.panmin.gtspro.data.local.PreferencesHelper;

public class LocaleManager {

    public static Context setLocale(Context context) {
        return updateResources(context, getLanguage());
    }

    public static void setNewLocale(Context context, String language) {
        persistLanguage(language);
        updateResources(context, language);
    }

    public static String getLanguage() {
        return PreferencesHelper.getLanguage();
    }

    @SuppressLint("ApplySharedPref")
    private static void persistLanguage(String language) {
        PreferencesHelper.setLanguage(language);
    }

    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }

    public static Locale getLocale(Resources res) {
        Configuration config = res.getConfiguration();
        return Build.VERSION.SDK_INT >= 24 ? config.getLocales().get(0) : config.locale;
    }

}