package ru.panmin.gtspro.data.models.wsrequests;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ru.panmin.gtspro.data.models.AnswerToQuestion;
import ru.panmin.gtspro.utils.Constants;

public class FormWsRequest extends BaseWsRequest {

    @SerializedName("data") private List<AnswerToQuestion> data = new ArrayList<>();

    public FormWsRequest() {
    }

    public FormWsRequest(String id, List<AnswerToQuestion> data) {
        super(id, Constants.WS_TYPE_FORM);
        this.data = data;
    }

    public List<AnswerToQuestion> getData() {
        return data;
    }

    public void setData(List<AnswerToQuestion> data) {
        this.data = data;
    }

}