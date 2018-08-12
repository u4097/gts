package ru.panmin.gtspro.data.models.wsrequests;

import com.google.gson.annotations.SerializedName;

import ru.panmin.gtspro.utils.GsonUtils;

public class BaseWsRequest {

    @SerializedName("id") private String id;
    @SerializedName("type") private String type;

    public BaseWsRequest() {
    }

    public BaseWsRequest(String id, String type) {
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

    public String toJsonString() {
        return GsonUtils.getGson().toJson(this);
    }

}