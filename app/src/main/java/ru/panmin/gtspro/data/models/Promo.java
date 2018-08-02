package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Promo extends RealmObject {

    @PrimaryKey private String id;
    @SerializedName("name") private String name;
    @SerializedName("author") private String author;
    @SerializedName("description") private String description;
    @SerializedName("sku") private String sku;
    @SerializedName("begin_date") private String beginDate;
    @SerializedName("finish_date") private String finishDate;
    @SerializedName("attachedME") private RealmList<String> attachedME = new RealmList<>();
    @SerializedName("clients") private RealmList<String> clients = new RealmList<>();
    @SerializedName("isActive") private Boolean isActive;

    public Promo() {
    }

    public Promo(String id, String name, String author, String description, String sku, String beginDate, String finishDate,
                 RealmList<String> attachedME, RealmList<String> clients, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.sku = sku;
        this.beginDate = beginDate;
        this.finishDate = finishDate;
        this.attachedME = attachedME;
        this.clients = clients;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public RealmList<String> getAttachedME() {
        return attachedME;
    }

    public void setAttachedME(RealmList<String> attachedME) {
        this.attachedME = attachedME;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public RealmList<String> getClients() {
        return clients;
    }

    public void setClients(RealmList<String> clients) {
        this.clients = clients;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}