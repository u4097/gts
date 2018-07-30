package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FullName implements Parcelable {

    public static final Parcelable.Creator<FullName> CREATOR = new Parcelable.Creator<FullName>() {
        @Override
        public FullName createFromParcel(Parcel source) {
            return new FullName(source);
        }

        @Override
        public FullName[] newArray(int size) {
            return new FullName[size];
        }
    };

    @SerializedName("ru") private String ru;
    @SerializedName("en") private String en;

    public FullName() {
    }

    public FullName(String ru, String en) {
        this.ru = ru;
        this.en = en;
    }

    private FullName(Parcel in) {
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