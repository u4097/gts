package ru.panmin.gtspro.data.models;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.LocaleManager;

public class Name extends RealmObject {

    @SerializedName("ru") private String ru;
    @SerializedName("en") private String en;

    public Name() {
    }

    public Name(String ru, String en) {
        this.ru = ru;
        this.en = en;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String toString(Context context) {
        switch (LocaleManager.getLanguage(context)) {
            case Constants.LANGUAGE_RUSSIAN:
                return ru;
            case Constants.LANGUAGE_ENGLISH:
                return en;
            default:
                return "";

        }
    }

}