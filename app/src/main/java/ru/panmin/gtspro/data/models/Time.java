package ru.panmin.gtspro.data.models;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.panmin.gtspro.utils.Constants;
import timber.log.Timber;

public class Time extends RealmObject {

    @PrimaryKey private String id = UUID.randomUUID().toString();
    @SerializedName("begin") private String begin;
    @SerializedName("end") private String end;

    public Time() {
    }

    public Time(String begin, String end) {
        this.begin = begin;
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Date getBeginAsDate() {
        Date date = new Date();
        try {
            date = Constants.SIMPLE_TIME_FORMAT.parse(begin);
        } catch (Exception e) {
            Timber.d(e);
        }
        return date;
    }

    public Date getEndAsDate() {
        Date date = new Date();
        try {
            date = Constants.SIMPLE_TIME_FORMAT.parse(end);
        } catch (Exception e) {
            Timber.d(e);
        }
        return date;
    }

    public String getBeginWithFormat(@NonNull SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(getBeginAsDate());
    }

    public String getEndWithFormat(@NonNull SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(getEndAsDate());
    }

    public String getBeginWithFormat(@NonNull String dateFormat) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(getBeginAsDate());
    }

    public String getEndWithFormat(@NonNull String dateFormat) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(getEndAsDate());
    }

    @SuppressLint("SimpleDateFormat")
    public String toString(@NonNull String dateFormat) {
        return toString(new SimpleDateFormat(dateFormat));
    }

    public String toString(@NonNull SimpleDateFormat simpleDateFormat) {
        return String.format("%s - %s", getBeginWithFormat(simpleDateFormat), getEndWithFormat(simpleDateFormat));
    }

    @Override
    public String toString() {
        return toString("HH:mm");
    }

}