package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

public class ClaimData {

    @SerializedName("claim_id") private String claimId;
    @SerializedName("answer") private ClaimAnswer answer;

    public ClaimData() {
    }

    public ClaimData(String claimId, ClaimAnswer answer) {
        this.claimId = claimId;
        this.answer = answer;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public ClaimAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(ClaimAnswer answer) {
        this.answer = answer;
    }

}