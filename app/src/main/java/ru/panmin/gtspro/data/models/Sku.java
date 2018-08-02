package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Sku extends RealmObject {

    @PrimaryKey private String id = UUID.randomUUID().toString();
    @SerializedName("client") private Client client;
    @SerializedName("sku_list") private RealmList<SkuListElement> skuList = new RealmList<>();

    public Sku() {
    }

    public Sku(Client client, RealmList<SkuListElement> skuList) {
        this.client = client;
        this.skuList = skuList;
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

    public RealmList<SkuListElement> getSkuList() {
        return skuList;
    }

    public void setSkuList(RealmList<SkuListElement> skuList) {
        this.skuList = skuList;
    }

}