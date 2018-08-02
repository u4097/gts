package ru.panmin.gtspro.data.models;

import io.realm.RealmObject;

public class Report extends RealmObject {

    private String id;

    public Report() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}