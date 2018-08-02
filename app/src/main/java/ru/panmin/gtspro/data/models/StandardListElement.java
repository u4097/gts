package ru.panmin.gtspro.data.models;

import io.realm.RealmObject;

public class StandardListElement extends RealmObject {

    private String id;

    public StandardListElement() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}