package ru.panmin.gtspro.data.models.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import ru.panmin.gtspro.data.models.FullName;

public class AuthResponse extends BaseResponse implements Parcelable {

    public static final Creator<AuthResponse> CREATOR = new Creator<AuthResponse>() {
        @Override
        public AuthResponse createFromParcel(Parcel source) {
            return new AuthResponse(source);
        }

        @Override
        public AuthResponse[] newArray(int size) {
            return new AuthResponse[size];
        }
    };

    @SerializedName("token") private String token;
    @SerializedName("id") private String id;
    @SerializedName("username") private String username;
    @SerializedName("role") private String role;
    @SerializedName("supervisor_id") private String supervisorId;
    @SerializedName("full_name") private FullName fullName;

    public AuthResponse() {
    }

    public AuthResponse(String token, String id, String username, String role, String supervisorId, FullName fullName) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.role = role;
        this.supervisorId = supervisorId;
        this.fullName = fullName;
    }

    private AuthResponse(Parcel in) {
        this.token = in.readString();
        this.id = in.readString();
        this.username = in.readString();
        this.role = in.readString();
        this.supervisorId = in.readString();
        this.fullName = in.readParcelable(FullName.class.getClassLoader());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.id);
        dest.writeString(this.username);
        dest.writeString(this.role);
        dest.writeString(this.supervisorId);
        dest.writeParcelable(this.fullName, flags);
    }

}