package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

public class VisitAcceptedData {

    @SerializedName("visit_id") private String visitId;

    public VisitAcceptedData() {
    }

    public VisitAcceptedData(String visitId) {
        this.visitId = visitId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

}