package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Promo implements Parcelable {

    public static final Parcelable.Creator<Promo> CREATOR = new Parcelable.Creator<Promo>() {
        @Override
        public Promo createFromParcel(Parcel source) {
            return new Promo(source);
        }

        @Override
        public Promo[] newArray(int size) {
            return new Promo[size];
        }
    };

    public Promo() {
    }

    private Promo(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

}