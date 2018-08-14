package ru.panmin.gtspro.data.models.wsrequests;

import com.google.gson.annotations.SerializedName;

import ru.panmin.gtspro.data.models.FormFillingTimeData;
import ru.panmin.gtspro.utils.Constants;

public class FormFillingTimeWsRequest extends BaseWsRequest {

    @SerializedName("data") private FormFillingTimeData data;

    public FormFillingTimeWsRequest() {
        super(Constants.WS_TYPE_FORM_FILLING_TIME);
    }

    public FormFillingTimeWsRequest(FormFillingTimeData data) {
        super(Constants.WS_TYPE_FORM_FILLING_TIME);
        this.data = data;
    }

    public FormFillingTimeData getData() {
        return data;
    }

    public void setData(FormFillingTimeData data) {
        this.data = data;
    }

}