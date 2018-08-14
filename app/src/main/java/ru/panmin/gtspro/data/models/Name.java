package ru.panmin.gtspro.data.models;

<<<<<<< HEAD
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

=======
>>>>>>> 3c257dd2fed21a42919011d44aaa62a49ca0fc9a
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.panmin.gtspro.utils.Constants;
import ru.panmin.gtspro.utils.LocaleManager;
import ru.panmin.gtspro.utils.TextUtils;

public class Name extends RealmObject implements Parcelable {

    @PrimaryKey private String id = UUID.randomUUID().toString();
    @SerializedName("ru") private String ru;
    @SerializedName("en") private String en;

    public Name() {
    }



    public Name(String ru, String en) {
        this.ru = ru;
        this.en = en;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        switch (LocaleManager.getLanguage()) {
            case Constants.LANGUAGE_RUSSIAN:
                return TextUtils.isEmpty(ru) ? TextUtils.isEmpty(en) ? "" : en : ru;
            case Constants.LANGUAGE_ENGLISH:
                return TextUtils.isEmpty(en) ? TextUtils.isEmpty(ru) ? "" : ru : en;
            default:
                return "";

        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.ru);
        dest.writeString(this.en);
    }

    protected Name(Parcel in) {
        this.id = in.readString();
        this.ru = in.readString();
        this.en = in.readString();
    }

    public static final Parcelable.Creator<Name> CREATOR = new Parcelable.Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel source) {
            return new Name(source);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };
}