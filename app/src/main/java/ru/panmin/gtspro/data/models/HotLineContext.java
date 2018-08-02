package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HotLineContext extends RealmObject {

    @PrimaryKey private String id;
    @SerializedName("name") private Name name;
    @SerializedName("comment_description") private String commentDescription;
    @SerializedName("hot_line_cause_id") private String hotLineCauseId;
    @SerializedName("position") private int position;

    public HotLineContext() {
    }

    public HotLineContext(String id, Name name, String commentDescription, String hotLineCauseId, int position) {
        this.id = id;
        this.name = name;
        this.commentDescription = commentDescription;
        this.hotLineCauseId = hotLineCauseId;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public String getHotLineCauseId() {
        return hotLineCauseId;
    }

    public void setHotLineCauseId(String hotLineCauseId) {
        this.hotLineCauseId = hotLineCauseId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}