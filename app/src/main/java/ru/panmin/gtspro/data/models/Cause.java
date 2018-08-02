package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

public class Cause extends RealmObject {

    @SerializedName("id") private String id;
    @SerializedName("position") private int position;
    @SerializedName("without_sku") private boolean withoutSku;
    @SerializedName("name") private Name name;

    public Cause() {
    }

    public Cause(String id, int position, boolean withoutSku, Name name) {
        this.id = id;
        this.position = position;
        this.withoutSku = withoutSku;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isWithoutSku() {
        return withoutSku;
    }

    public void setWithoutSku(boolean withoutSku) {
        this.withoutSku = withoutSku;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

}