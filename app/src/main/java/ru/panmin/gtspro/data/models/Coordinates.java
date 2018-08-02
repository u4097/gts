package ru.panmin.gtspro.data.models;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Coordinates extends RealmObject {

    @SerializedName("latitude") private double latitude;
    @SerializedName("longitude") private double longitude;

    public Coordinates() {
    }

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Location toLocation() {
        Location location = new Location("dummyprovider");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
    }

}