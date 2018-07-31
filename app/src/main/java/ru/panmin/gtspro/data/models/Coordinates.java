package ru.panmin.gtspro.data.models;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Coordinates implements Parcelable {

    public static final Parcelable.Creator<Coordinates> CREATOR = new Parcelable.Creator<Coordinates>() {
        @Override
        public Coordinates createFromParcel(Parcel source) {
            return new Coordinates(source);
        }

        @Override
        public Coordinates[] newArray(int size) {
            return new Coordinates[size];
        }
    };

    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;

    public Coordinates() {
    }

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private Coordinates(Parcel in) {
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

}