package ru.panmin.gtspro.data.models.wsrequests;

import com.google.gson.annotations.SerializedName;

import ru.panmin.gtspro.data.models.HotLineData;
import ru.panmin.gtspro.utils.Constants;

public class HotLineWsRequest extends BaseWsRequest {

    @SerializedName("data") private HotLineData data;

    public HotLineWsRequest() {
        super(Constants.WS_TYPE_HOT_LINE);
    }

    public HotLineWsRequest(HotLineData data) {
        super(Constants.WS_TYPE_HOT_LINE);
        this.data = data;
    }

    public HotLineData getData() {
        return data;
    }

    public void setData(HotLineData data) {
        this.data = data;
    }

}