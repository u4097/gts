package ru.panmin.gtspro.data.models.wsresponses;

import com.google.gson.annotations.SerializedName;

public class BaseWsResponse {

    @SerializedName("id") private String id;
    @SerializedName("type") private String type;

    public BaseWsResponse() {
    }

    public BaseWsResponse(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}