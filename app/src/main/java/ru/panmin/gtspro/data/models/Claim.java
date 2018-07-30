package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Claim implements Parcelable {

    public static final Parcelable.Creator<Claim> CREATOR = new Parcelable.Creator<Claim>() {
        @Override
        public Claim createFromParcel(Parcel source) {
            return new Claim(source);
        }

        @Override
        public Claim[] newArray(int size) {
            return new Claim[size];
        }
    };

    public Claim() {
    }

    private Claim(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

}