package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class EndVisitData {

    @SerializedName("visit_id") private String visitId;
    @SerializedName("is_auto_exit") private boolean isAutoExit;
    @SerializedName("date") private Date date;
    @SerializedName("coordinates") private Coordinates coordinates;

    public EndVisitData() {
    }

    public EndVisitData(String visitId, boolean isAutoExit, Date date, double latitude, double longitude) {
        this.visitId = visitId;
        this.isAutoExit = isAutoExit;
        this.date = date;
        this.coordinates = new Coordinates(latitude, longitude);
    }

    public EndVisitData(String visitId, boolean isAutoExit, Date date, Coordinates coordinates) {
        this.visitId = visitId;
        this.isAutoExit = isAutoExit;
        this.date = date;
        this.coordinates = coordinates;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public boolean isAutoExit() {
        return isAutoExit;
    }

    public void setAutoExit(boolean autoExit) {
        isAutoExit = autoExit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}