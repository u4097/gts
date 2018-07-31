package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Standard implements Parcelable {

    public static final Parcelable.Creator<Standard> CREATOR = new Parcelable.Creator<Standard>() {
        @Override
        public Standard createFromParcel(Parcel source) {
            return new Standard(source);
        }

        @Override
        public Standard[] newArray(int size) {
            return new Standard[size];
        }
    };

    @SerializedName("client")
    private Client client;
    @SerializedName("standard_list")
    private List<StandardListElement> standardList = new ArrayList<>();

    public Standard() {
    }

    public Standard(Client client, List<StandardListElement> standardList) {
        this.client = client;
        this.standardList = standardList;
    }

    private Standard(Parcel in) {
        this.client = in.readParcelable(Client.class.getClassLoader());
        this.standardList = in.createTypedArrayList(StandardListElement.CREATOR);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<StandardListElement> getStandardList() {
        return standardList;
    }

    public void setStandardList(List<StandardListElement> standardList) {
        this.standardList = standardList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.client, flags);
        dest.writeTypedList(this.standardList);
    }

}