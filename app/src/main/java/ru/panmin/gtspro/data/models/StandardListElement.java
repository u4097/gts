package ru.panmin.gtspro.data.models;

import java.io.Serializable;

import io.realm.RealmObject;

public class StandardListElement extends RealmObject {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StandardListElement() {
    }

}