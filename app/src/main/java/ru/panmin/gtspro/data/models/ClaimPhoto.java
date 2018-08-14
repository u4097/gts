package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

public class ClaimPhoto {

    @SerializedName("file_id") private String fileId;
    @SerializedName("comment") private String comment;

    public ClaimPhoto() {
    }

    public ClaimPhoto(String fileId, String comment) {
        this.fileId = fileId;
        this.comment = comment;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}