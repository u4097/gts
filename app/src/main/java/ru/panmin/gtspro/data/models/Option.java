package ru.panmin.gtspro.data.models;

import io.realm.RealmObject;

public class Option extends RealmObject {

    private String id;

    public Option() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}