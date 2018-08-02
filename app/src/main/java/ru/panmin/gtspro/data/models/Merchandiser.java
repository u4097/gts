package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Merchandiser extends RealmObject {

    @PrimaryKey private String name;
    @SerializedName("clients") private RealmList<Client> clients = new RealmList<>();
    @SerializedName("times") private RealmList<Time> times = new RealmList<>();

    public Merchandiser() {
    }

    public Merchandiser(String name, RealmList<Client> clients, RealmList<Time> times) {
        this.name = name;
        this.clients = clients;
        this.times = times;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Client> getClients() {
        return clients;
    }

    public void setClients(RealmList<Client> clients) {
        this.clients = clients;
    }

    public RealmList<Time> getTimes() {
        return times;
    }

    public void setTimes(RealmList<Time> times) {
        this.times = times;
    }

}