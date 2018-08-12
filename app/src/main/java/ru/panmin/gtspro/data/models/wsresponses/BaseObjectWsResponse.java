package ru.panmin.gtspro.data.models.wsresponses;

import com.google.gson.annotations.SerializedName;

public class BaseObjectWsResponse<T> extends BaseWsResponse {

    @SerializedName("data") private T data;

    public BaseObjectWsResponse() {
    }

    public BaseObjectWsResponse(String id, String type, T data) {
        super(id, type);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}