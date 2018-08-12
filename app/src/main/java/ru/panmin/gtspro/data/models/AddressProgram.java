package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;

public class AddressProgram {

    @SerializedName("auto_checkout_time") private int autoCheckoutTime;
    @SerializedName("trade_point_radius") private int tradePointRadius;
    @SerializedName("hot_line") private HotLine hotLine;
    @SerializedName("trade_points") private RealmList<TradePoint> tradePoints = new RealmList<>();

    public AddressProgram() {
    }

    public AddressProgram(List<TradePoint> tradePoints) {
        this.tradePoints.clear();
        if (tradePoints != null && !tradePoints.isEmpty()) {
            this.tradePoints.addAll(tradePoints);
        }
    }

    public AddressProgram(int autoCheckoutTime, int tradePointRadius, HotLine hotLine, RealmList<TradePoint> tradePoints) {
        this.autoCheckoutTime = autoCheckoutTime;
        this.tradePointRadius = tradePointRadius;
        this.hotLine = hotLine;
        this.tradePoints = tradePoints;
    }

    public int getAutoCheckoutTime() {
        return autoCheckoutTime;
    }

    public void setAutoCheckoutTime(int autoCheckoutTime) {
        this.autoCheckoutTime = autoCheckoutTime;
    }

    public int getTradePointRadius() {
        return tradePointRadius;
    }

    public void setTradePointRadius(int tradePointRadius) {
        this.tradePointRadius = tradePointRadius;
    }

    public HotLine getHotLine() {
        return hotLine;
    }

    public void setHotLine(HotLine hotLine) {
        this.hotLine = hotLine;
    }

    public RealmList<TradePoint> getTradePoints() {
        return tradePoints;
    }

    public void setTradePoints(RealmList<TradePoint> tradePoints) {
        this.tradePoints = tradePoints;
    }

}