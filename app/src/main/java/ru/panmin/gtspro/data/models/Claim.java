package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Claim extends RealmObject {

    @PrimaryKey
    private String id;
    @SerializedName("name") private Name name;
    @SerializedName("claim_text") private Name claimText;
    @SerializedName("message_text") private Name messageText;
    @SerializedName("author") private Name author;
    @SerializedName("begin_date") private String beginDate;
    @SerializedName("finish_date") private String finishDate;
    @SerializedName("type") private Type type;


    public Claim() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Claim(String id, Name name, Name claimText, Name messageText, Name author, String beginDate, String finishDate, Type type) {
        this.id = id;
        this.name = name;
        this.claimText = claimText;
        this.messageText = messageText;
        this.author = author;
        this.beginDate = beginDate;
        this.finishDate = finishDate;
        this.type = type;
    }

    public Name getName() {
        return name;
    }

    public Name getClaimText() {
        return claimText;
    }

    public Name getMessageText() {
        return messageText;
    }

    public Name getAuthor() {
        return author;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public Type getType() {
        return type;
    }
}