package ru.panmin.gtspro.data.models.wsrequests;

import com.google.gson.annotations.SerializedName;

import ru.panmin.gtspro.data.models.EndVisitData;
import ru.panmin.gtspro.utils.Constants;

public class EndVisitWsRequest extends BaseWsRequest {

    @SerializedName("data") private EndVisitData data;

    public EndVisitWsRequest() {
        super(Constants.WS_TYPE_END_VISIT);
    }

    public EndVisitWsRequest(EndVisitData data) {
        super(Constants.WS_TYPE_END_VISIT);
        this.data = data;
    }

    public EndVisitData getData() {
        return data;
    }

    public void setData(EndVisitData data) {
        this.data = data;
    }

}