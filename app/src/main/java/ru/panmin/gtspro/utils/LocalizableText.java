package ru.panmin.gtspro.utils;

import android.content.res.Resources;
import android.text.TextUtils;

import java.util.Locale;

public class LocalizableText {
    String en = "";
    String ru = "";

    public LocalizableText(String en, String ru) {
        if (!TextUtils.isEmpty(en)){
            this.en = en;
        }
        if (!TextUtils.isEmpty(ru)) {
            this.ru = ru;
        }
    }


     public String text(Resources resources) {
         return text(resources.getConfiguration().locale);
    }

    String text(Locale locale) {
        switch (locale.getLanguage()) {
            case "ru": return ru;
            case "en": return en;
            default: return "Invalid locale";
        }
    }
}
