package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HotLineData {

    @SerializedName("client_id") private String clientId;
    @SerializedName("trade_point_id") private String tradePointId;
    @SerializedName("context_id") private String contextId;
    @SerializedName("sku_ids") private List<String> skuIds = new ArrayList<>();
    @SerializedName("comment") private String comment;

    public HotLineData() {
    }

    public HotLineData(String clientId, String tradePointId, String contextId) {
        this.clientId = clientId;
        this.tradePointId = tradePointId;
        this.contextId = contextId;
    }

    public HotLineData(String clientId, String tradePointId, String contextId, List<String> skuIds, String comment) {
        this.clientId = clientId;
        this.tradePointId = tradePointId;
        this.contextId = contextId;
        this.skuIds = skuIds;
        this.comment = comment;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTradePointId() {
        return tradePointId;
    }

    public void setTradePointId(String tradePointId) {
        this.tradePointId = tradePointId;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public List<String> getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(List<String> skuIds) {
        this.skuIds = skuIds;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}