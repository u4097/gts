package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Sku implements Parcelable {

    public static final Parcelable.Creator<Sku> CREATOR = new Parcelable.Creator<Sku>() {
        @Override
        public Sku createFromParcel(Parcel source) {
            return new Sku(source);
        }

        @Override
        public Sku[] newArray(int size) {
            return new Sku[size];
        }
    };

    @SerializedName("client") private Client client;
    @SerializedName("sku_list") private List<SkuListElement> skuList = new ArrayList<>();

    public Sku() {
    }

    public Sku(Client client, List<SkuListElement> skuList) {
        this.client = client;
        this.skuList = skuList;
    }

    private Sku(Parcel in) {
        this.client = in.readParcelable(Client.class.getClassLoader());
        this.skuList = in.createTypedArrayList(SkuListElement.CREATOR);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<SkuListElement> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SkuListElement> skuList) {
        this.skuList = skuList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.client, flags);
        dest.writeTypedList(this.skuList);
    }

}