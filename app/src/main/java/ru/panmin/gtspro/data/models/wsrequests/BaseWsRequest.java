package ru.panmin.gtspro.data.models.wsrequests;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class BaseWsRequest {

    @SerializedName("type") private String type;

    public BaseWsRequest() {
    }

    public BaseWsRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toJsonString() {
        return new Gson().toJson(this);
    }

}