package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Photo extends RealmObject {

    @PrimaryKey private String id;
    @SerializedName("url") private String url;
    @SerializedName("comment") private String comment;

    public Photo() {
    }

    public Photo(String id, String url, String comment) {
        this.id = id;
        this.url = url;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}