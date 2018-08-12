package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MerchandiserVisitsData {

    @SerializedName("me_id") private String meId;
    @SerializedName("trade_point_id") private String tradePointId;
    @SerializedName("is_checkin") private boolean isCheckin;
    @SerializedName("visits") private List<Visit> visits = new ArrayList<>();

    public MerchandiserVisitsData() {
    }

    public MerchandiserVisitsData(String meId, String tradePointId, boolean isCheckin, List<Visit> visits) {
        this.meId = meId;
        this.tradePointId = tradePointId;
        this.isCheckin = isCheckin;
        this.visits = visits;
    }

    public String getMeId() {
        return meId;
    }

    public void setMeId(String meId) {
        this.meId = meId;
    }

    public String getTradePointId() {
        return tradePointId;
    }

    public void setTradePointId(String tradePointId) {
        this.tradePointId = tradePointId;
    }

    public boolean isCheckin() {
        return isCheckin;
    }

    public void setCheckin(boolean checkin) {
        isCheckin = checkin;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

}