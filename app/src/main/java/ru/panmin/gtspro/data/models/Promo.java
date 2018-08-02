package ru.panmin.gtspro.data.models;

import io.realm.RealmObject;

public class Promo extends RealmObject {

    private String id;

    public Promo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}