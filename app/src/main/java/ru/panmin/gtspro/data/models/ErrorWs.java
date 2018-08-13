package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ErrorWs {

    @SerializedName("errors") private List<String> errors = new ArrayList<>();
    @SerializedName("status_code") private Integer statusCode;
    @SerializedName("is_error") private Boolean isError;

    public ErrorWs() {
    }

    public ErrorWs(List<String> errors, Integer statusCode, Boolean isError) {
        this.errors = errors;
        this.statusCode = statusCode;
        this.isError = isError;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

}