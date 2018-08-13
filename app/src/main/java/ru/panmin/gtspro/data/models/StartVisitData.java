package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class StartVisitData {

    @SerializedName("visit_id") private String visitId;
    @SerializedName("trade_point_id") private String tradePointId;
    @SerializedName("date") private Date date;
    @SerializedName("coordinates") private Coordinates coordinates;

    public StartVisitData() {
    }

    public StartVisitData(String visitId, String tradePointId, Date date, double latitude, double longitude) {
        this.visitId = visitId;
        this.tradePointId = tradePointId;
        this.date = date;
        this.coordinates = new Coordinates(latitude, longitude);
    }

    public StartVisitData(String visitId, String tradePointId, Date date, Coordinates coordinates) {
        this.visitId = visitId;
        this.tradePointId = tradePointId;
        this.date = date;
        this.coordinates = coordinates;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getTradePointId() {
        return tradePointId;
    }

    public void setTradePointId(String tradePointId) {
        this.tradePointId = tradePointId;
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