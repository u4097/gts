package ru.panmin.gtspro.data.models;

import io.realm.RealmObject;

public class Claim extends RealmObject {

    private String id;

    public Claim() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}