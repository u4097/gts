package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClaimAnswer {

    @SerializedName("visit_id") private String visitId;
    @SerializedName("text") private String text;
    @SerializedName("photos") private List<ClaimPhoto> photos = new ArrayList<>();
    @SerializedName("date") private Date date;

    public ClaimAnswer() {
    }

    public ClaimAnswer(String visitId, String text, List<ClaimPhoto> photos, Date date) {
        this.visitId = visitId;
        this.text = text;
        this.photos = photos;
        this.date = date;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ClaimPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<ClaimPhoto> photos) {
        this.photos = photos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}