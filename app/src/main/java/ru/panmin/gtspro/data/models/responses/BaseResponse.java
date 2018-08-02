package ru.panmin.gtspro.data.models.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ru.panmin.gtspro.data.models.Error;

public class BaseResponse implements Serializable {

    @SerializedName("errors") private Error errors;
    @SerializedName("status_code") private int statusCode;
    @SerializedName("is_error") private boolean error;

    public BaseResponse() {
    }

    public BaseResponse(Error errors, int statusCode, boolean error) {
        this.errors = errors;
        this.statusCode = statusCode;
        this.error = error;
    }

    public Error getErrors() {
        return errors;
    }

    public void setErrors(Error errors) {
        this.errors = errors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}