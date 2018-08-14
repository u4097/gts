package ru.panmin.gtspro.data.models;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Answer extends RealmObject {

    @PrimaryKey private String id = UUID.randomUUID().toString();
    private Boolean booleanValue = null;
    private Double doubleValue = null;
    private Integer integerValue = null;
    private String stringValue = null;
    private RealmList<Integer> integerList = new RealmList<>();
    private RealmList<Photo> photoList = new RealmList<>();

    public Answer() {
    }

    public Answer(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Answer(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Answer(Integer integerValue) {
        this.integerValue = integerValue;
    }

    public Answer(String stringValue) {
        this.stringValue = stringValue;
    }

    public Answer(RealmList realmList) {
        if (realmList == null || realmList.isEmpty()) {
            integerList = new RealmList<>();
            photoList = new RealmList<>();
        } else if (realmList.get(0) instanceof Photo) {
            integerList = new RealmList<>();
            photoList = realmList;
        } else if (realmList.get(0) instanceof Integer) {
            integerList = realmList;
            photoList = new RealmList<>();
        } else {
            integerList = new RealmList<>();
            photoList = new RealmList<>();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public RealmList<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(RealmList<Integer> integerList) {
        this.integerList = integerList;
    }

    public RealmList<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(RealmList<Photo> photoList) {
        this.photoList = photoList;
    }

}