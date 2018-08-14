package ru.panmin.gtspro.data.models.wsrequests;

import com.google.gson.annotations.SerializedName;

import ru.panmin.gtspro.data.models.StartVisitData;
import ru.panmin.gtspro.utils.Constants;

public class StartVisitWsRequest extends BaseWsRequest {

    @SerializedName("data") private StartVisitData data;

    public StartVisitWsRequest() {
        super(Constants.WS_TYPE_START_VISIT);
    }

    public StartVisitWsRequest(StartVisitData data) {
        super(Constants.WS_TYPE_START_VISIT);
        this.data = data;
    }

    public StartVisitData getData() {
        return data;
    }

    public void setData(StartVisitData data) {
        this.data = data;
    }

}