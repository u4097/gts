package ru.panmin.gtspro.data.models.wsresponses;

import com.google.gson.annotations.SerializedName;

public class BaseWsResponse {

    @SerializedName("id") private String id;
    @SerializedName("type") private String type;
    @SerializedName("status_code") private Integer statusCode;
    @SerializedName("is_error") private Boolean isError;
    @SerializedName("error") private String error;

    public BaseWsResponse() {
    }

    public BaseWsResponse(String id, String type, Integer statusCode, Boolean isError, String error) {
        this.id = id;
        this.type = type;
        this.statusCode = statusCode;
        this.isError = isError;
        this.error = error;
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

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean isError() {
        return isError != null && isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}