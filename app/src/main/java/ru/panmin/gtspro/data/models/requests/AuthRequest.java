package ru.panmin.gtspro.data.models.requests;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AuthRequest implements Parcelable {

    public static final Parcelable.Creator<AuthRequest> CREATOR = new Parcelable.Creator<AuthRequest>() {
        @Override
        public AuthRequest createFromParcel(Parcel source) {
            return new AuthRequest(source);
        }

        @Override
        public AuthRequest[] newArray(int size) {
            return new AuthRequest[size];
        }
    };

    @SerializedName("username") private String userName;
    @SerializedName("password") private String password;

    public AuthRequest() {
    }

    public AuthRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    private AuthRequest(Parcel in) {
        this.userName = in.readString();
        this.password = in.readString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.password);
    }

}