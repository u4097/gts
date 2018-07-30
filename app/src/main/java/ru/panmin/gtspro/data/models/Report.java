package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Report implements Parcelable {

    public static final Parcelable.Creator<Report> CREATOR = new Parcelable.Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel source) {
            return new Report(source);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    public Report() {
    }

    private Report(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

}