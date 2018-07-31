package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SubBrand implements Parcelable {

    public static final Parcelable.Creator<SubBrand> CREATOR = new Parcelable.Creator<SubBrand>() {
        @Override
        public SubBrand createFromParcel(Parcel source) {
            return new SubBrand(source);
        }

        @Override
        public SubBrand[] newArray(int size) {
            return new SubBrand[size];
        }
    };

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private Name name;

    public SubBrand() {
    }

    public SubBrand(String id, Name name) {
        this.id = id;
        this.name = name;
    }

    private SubBrand(Parcel in) {
        this.id = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.name, flags);
    }

}