package ru.panmin.gtspro.data.models.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmModel;
import ru.panmin.gtspro.data.models.Name;

public class UserInfoResponse extends BaseResponse implements Serializable {

    @SerializedName("id") private String id;
    @SerializedName("username") private String username;
    @SerializedName("role") private String role;
    @SerializedName("supervisor_id") private String supervisorId;
    @SerializedName("full_name") private Name fullName;

    public UserInfoResponse() {
    }

    public UserInfoResponse(String id, String username, String role, String supervisorId, Name fullName) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.supervisorId = supervisorId;
        this.fullName = fullName;
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

    public Name getFullName() {
        return fullName;
    }

    public void setFullName(Name fullName) {
        this.fullName = fullName;
    }

}