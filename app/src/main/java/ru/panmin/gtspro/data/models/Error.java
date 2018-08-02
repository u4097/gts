package ru.panmin.gtspro.data.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Error extends RealmObject {

    @PrimaryKey private String detail;

    public Error() {
    }

    public Error(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}