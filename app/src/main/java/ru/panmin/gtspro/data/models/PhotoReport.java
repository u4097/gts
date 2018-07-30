package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PhotoReport implements Parcelable {

    public static final Parcelable.Creator<PhotoReport> CREATOR = new Parcelable.Creator<PhotoReport>() {
        @Override
        public PhotoReport createFromParcel(Parcel source) {
            return new PhotoReport(source);
        }

        @Override
        public PhotoReport[] newArray(int size) {
            return new PhotoReport[size];
        }
    };

    public PhotoReport() {
    }

    private PhotoReport(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

}