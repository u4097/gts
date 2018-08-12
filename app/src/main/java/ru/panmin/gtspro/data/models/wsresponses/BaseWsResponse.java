package ru.panmin.gtspro.data.models.wsresponses;

import com.google.gson.annotations.SerializedName;

public class BaseWsResponse {

    @SerializedName("status_code") private Integer statusCode;
    @SerializedName("is_error") private Boolean isError;
    @SerializedName("error") private String error;

    public BaseWsResponse() {
    }

    public BaseWsResponse(Integer statusCode, Boolean isError, String error) {
        this.statusCode = statusCode;
        this.isError = isError;
        this.error = error;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean isError() {
        return isError;
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