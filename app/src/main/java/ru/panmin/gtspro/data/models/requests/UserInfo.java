package ru.panmin.gtspro.data.models.requests;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("id") private String id;
    @SerializedName("username") private String username;
    @SerializedName("role") private String role;
    @SerializedName("supervisor_id") private String supervisorId;

    public UserInfo() {
    }

    public UserInfo(String id, String username, String role, String supervisorId) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.supervisorId = supervisorId;
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

}