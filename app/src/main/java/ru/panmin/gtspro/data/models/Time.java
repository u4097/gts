package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Time implements Parcelable {

    public static final Parcelable.Creator<Time> CREATOR = new Parcelable.Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel source) {
            return new Time(source);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };

    @SerializedName("begin") private Date begin;
    @SerializedName("end") private Date end;

    public Time() {
    }

    public Time(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    private Time(Parcel in) {
        long tmpBegin = in.readLong();
        this.begin = tmpBegin == -1 ? null : new Date(tmpBegin);
        long tmpEnd = in.readLong();
        this.end = tmpEnd == -1 ? null : new Date(tmpEnd);
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.begin != null ? this.begin.getTime() : -1);
        dest.writeLong(this.end != null ? this.end.getTime() : -1);
    }

}