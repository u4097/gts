package ru.panmin.gtspro.data.models.wsresponses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BaseListWsResponse<T> extends BaseWsResponse {

    @SerializedName("data") private List<T> data = new ArrayList<>();

    public BaseListWsResponse() {
    }

    public BaseListWsResponse(String id, String type, List<T> data) {
        super(id, type);
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}