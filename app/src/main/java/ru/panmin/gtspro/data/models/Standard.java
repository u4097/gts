package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Standard extends RealmObject {

    @PrimaryKey private String id = UUID.randomUUID().toString();
    @SerializedName("client") private Client client;
    @SerializedName("standard_list") private RealmList<StandardListElement> standardList = new RealmList<>();

    public Standard() {
    }

    public Standard(Client client, RealmList<StandardListElement> standardList) {
        this.client = client;
        this.standardList = standardList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public RealmList<StandardListElement> getStandardList() {
        return standardList;
    }

    public void setStandardList(RealmList<StandardListElement> standardList) {
        this.standardList = standardList;
    }

}