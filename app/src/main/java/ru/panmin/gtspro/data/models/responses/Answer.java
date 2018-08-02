package ru.panmin.gtspro.data.models.responses;

import io.realm.RealmObject;

public class Answer extends RealmObject {

    private String id;

    public Answer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
