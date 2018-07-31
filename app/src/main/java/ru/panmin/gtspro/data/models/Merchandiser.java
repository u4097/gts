package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Merchandiser implements Parcelable {

    public static final Creator<Merchandiser> CREATOR = new Creator<Merchandiser>() {
        @Override
        public Merchandiser createFromParcel(Parcel source) {
            return new Merchandiser(source);
        }

        @Override
        public Merchandiser[] newArray(int size) {
            return new Merchandiser[size];
        }
    };

    @SerializedName("name") private String name;
    @SerializedName("clients") private List<Client> clients = new ArrayList<>();
    @SerializedName("times") private List<Time> times = new ArrayList<>();

    public Merchandiser() {
    }

    public Merchandiser(String name, List<Client> clients, List<Time> times) {
        this.name = name;
        this.clients = clients;
        this.times = times;
    }

    private Merchandiser(Parcel in) {
        this.name = in.readString();
        this.clients = in.createTypedArrayList(Client.CREATOR);
        this.times = in.createTypedArrayList(Time.CREATOR);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeTypedList(this.clients);
        dest.writeTypedList(this.times);
    }

}