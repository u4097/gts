package ru.panmin.gtspro.data.models.wsrequests;

import com.google.gson.annotations.SerializedName;

import ru.panmin.gtspro.data.models.ClaimData;
import ru.panmin.gtspro.utils.Constants;

public class ClaimWsRequest extends BaseWsRequest {

    @SerializedName("data") private ClaimData data;

    public ClaimWsRequest() {
        super(Constants.WS_TYPE_CLAIM);
    }

    public ClaimWsRequest(ClaimData data) {
        super(Constants.WS_TYPE_CLAIM);
        this.data = data;
    }

    public ClaimData getData() {
        return data;
    }

    public void setData(ClaimData data) {
        this.data = data;
    }

}