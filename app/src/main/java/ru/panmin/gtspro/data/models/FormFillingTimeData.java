package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

public class FormFillingTimeData {

    @SerializedName("form_id") private String formId;
    @SerializedName("time") private int time;

    public FormFillingTimeData() {
    }

    public FormFillingTimeData(String formId, int time) {
        this.formId = formId;
        this.time = time;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}