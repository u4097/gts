package ru.panmin.gtspro.data.models;

import io.realm.RealmObject;

public class PhotoReport extends RealmObject {

    private String id;

    public PhotoReport() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}