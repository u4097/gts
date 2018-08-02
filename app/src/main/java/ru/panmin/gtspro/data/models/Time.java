package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Time extends RealmObject {

    @PrimaryKey private String id = UUID.randomUUID().toString();
    @SerializedName("begin") private String begin;
    @SerializedName("end") private String end;

    public Time() {
    }

    public Time(String begin, String end) {
        this.begin = begin;
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

}