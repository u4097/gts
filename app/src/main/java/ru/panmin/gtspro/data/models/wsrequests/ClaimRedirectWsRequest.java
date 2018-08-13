package ru.panmin.gtspro.data.models.wsrequests;

import com.google.gson.annotations.SerializedName;

import ru.panmin.gtspro.data.models.ClaimRedirectData;
import ru.panmin.gtspro.utils.Constants;

public class ClaimRedirectWsRequest extends BaseWsRequest {

    @SerializedName("data") private ClaimRedirectData data;

    public ClaimRedirectWsRequest() {
        super(Constants.WS_TYPE_CLAIM_REDIRECT);
    }

    public ClaimRedirectWsRequest(ClaimRedirectData data) {
        super(Constants.WS_TYPE_CLAIM_REDIRECT);
        this.data = data;
    }

    public ClaimRedirectData getData() {
        return data;
    }

    public void setData(ClaimRedirectData data) {
        this.data = data;
    }

}