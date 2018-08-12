package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

public class ClaimRedirectData {

    @SerializedName("claim_id") private String claimId;
    @SerializedName("merchandiser_id") private String merchandiserId;

    public ClaimRedirectData() {
    }

    public ClaimRedirectData(String claimId, String merchandiserId) {
        this.claimId = claimId;
        this.merchandiserId = merchandiserId;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getMerchandiserId() {
        return merchandiserId;
    }

    public void setMerchandiserId(String merchandiserId) {
        this.merchandiserId = merchandiserId;
    }

}