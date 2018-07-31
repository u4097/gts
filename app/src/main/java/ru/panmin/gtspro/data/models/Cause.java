package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Cause implements Parcelable {

    public static final Parcelable.Creator<Cause> CREATOR = new Parcelable.Creator<Cause>() {
        @Override
        public Cause createFromParcel(Parcel source) {
            return new Cause(source);
        }

        @Override
        public Cause[] newArray(int size) {
            return new Cause[size];
        }
    };

    @SerializedName("id")
    private String id;
    @SerializedName("position")
    private int position;
    @SerializedName("without_sku")
    private boolean withoutSku;
    @SerializedName("name")
    private Name name;

    public Cause() {
    }

    public Cause(String id, int position, boolean withoutSku, Name name) {
        this.id = id;
        this.position = position;
        this.withoutSku = withoutSku;
        this.name = name;
    }

    private Cause(Parcel in) {
        this.id = in.readString();
        this.position = in.readInt();
        this.withoutSku = in.readByte() != 0;
        this.name = in.readParcelable(Name.class.getClassLoader());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isWithoutSku() {
        return withoutSku;
    }

    public void setWithoutSku(boolean withoutSku) {
        this.withoutSku = withoutSku;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.position);
        dest.writeByte(this.withoutSku ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.name, flags);
    }

}