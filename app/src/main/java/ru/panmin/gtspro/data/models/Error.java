package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Error implements Parcelable {

    public static final Parcelable.Creator<Error> CREATOR = new Parcelable.Creator<Error>() {
        @Override
        public Error createFromParcel(Parcel source) {
            return new Error(source);
        }

        @Override
        public Error[] newArray(int size) {
            return new Error[size];
        }
    };

    @SerializedName("detail") private String detail;

    public Error() {
    }

    public Error(String detail) {
        this.detail = detail;
    }

    private Error(Parcel in) {
        this.detail = in.readString();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.detail);
    }

}