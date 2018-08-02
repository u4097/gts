package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class SubBrand extends RealmObject {

    @SerializedName("id") private String id;
    @SerializedName("name") private Name name;

    public SubBrand() {
    }

    public SubBrand(String id, Name name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

}