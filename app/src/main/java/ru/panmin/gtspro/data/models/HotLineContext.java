package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class HotLineContext implements Parcelable {

    public static final Parcelable.Creator<HotLineContext> CREATOR = new Parcelable.Creator<HotLineContext>() {
        @Override
        public HotLineContext createFromParcel(Parcel source) {
            return new HotLineContext(source);
        }

        @Override
        public HotLineContext[] newArray(int size) {
            return new HotLineContext[size];
        }
    };

    @SerializedName("id") private String id;
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

    private HotLineContext(Parcel in) {
        this.id = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.commentDescription = in.readString();
        this.hotLineCauseId = in.readString();
        this.position = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.name, flags);
        dest.writeString(this.commentDescription);
        dest.writeString(this.hotLineCauseId);
        dest.writeInt(this.position);
    }

}