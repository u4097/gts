package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Visit {

    @SerializedName("in_date") private Date inDate;
    @SerializedName("out_date") private Date outDate;
    @SerializedName("is_auto_exit") private boolean isAutoExit;

    public Visit() {
    }

    public Visit(Date inDate, Date outDate, boolean isAutoExit) {
        this.inDate = inDate;
        this.outDate = outDate;
        this.isAutoExit = isAutoExit;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public boolean isAutoExit() {
        return isAutoExit;
    }

    public void setAutoExit(boolean autoExit) {
        isAutoExit = autoExit;
    }

}