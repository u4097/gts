package ru.panmin.gtspro.data.models.wsresponses;

import com.google.gson.annotations.SerializedName;

public class BaseObjectWsResponse<T> extends BaseWsResponse {

    @SerializedName("data") private T data;

    public BaseObjectWsResponse() {
    }

    public BaseObjectWsResponse(String id, String type, Integer statusCode, Boolean isError, String error, T data) {
        super(id, type, statusCode, isError, error);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}