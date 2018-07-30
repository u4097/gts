package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Name implements Parcelable {

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

    @SerializedName("ru") private String ru;
    @SerializedName("en") private String en;

    public Name() {
    }

    public Name(String ru, String en) {
        this.ru = ru;
        this.en = en;
    }

    private Name(Parcel in) {
        this.ru = in.readString();
        this.en = in.readString();
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ru);
        dest.writeString(this.en);
    }

}