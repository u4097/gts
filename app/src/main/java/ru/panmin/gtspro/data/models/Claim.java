package ru.panmin.gtspro.data.models;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.panmin.gtspro.utils.Constants;
import timber.log.Timber;

public class Claim extends RealmObject {

    @PrimaryKey
    private String id;
    @SerializedName("number") private String number;
    @SerializedName("trade_point_id") private String tradePointId;
    @SerializedName("client_id") private String clientId;
    @SerializedName("type") private Type type;
    @SerializedName("text") private String text;

    @SerializedName("appoint_date") private String appointDate;
    @SerializedName("creation_date") private String creationDate;
    @SerializedName("answer_before_date") private String answerBeforeDate;
    @SerializedName("photos") private RealmList<Photo> photos;


    public Claim() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTradePointId() {
        return tradePointId;
    }

    public void setTradePointId(String tradePointId) {
        this.tradePointId = tradePointId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(String appointDate) {
        this.appointDate = appointDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getAnswerBeforeDate() {
        return answerBeforeDate;
    }

    public void setAnswerBeforeDate(String answerBeforeDate) {
        this.answerBeforeDate = answerBeforeDate;
    }


    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(RealmList<Photo> photos) {
        this.photos = photos;
    }

    public Date getCreationDateAsDate() {
        Date date = new Date();
        try {
            date = Constants.SIMPLE_DATE_FORMAT.parse(creationDate);
        } catch (Exception e) {
            Timber.d(e);
        }
        return date;
    }

    public String getCreationDateWithFormat(@NonNull SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(getCreationDateAsDate());
    }

    @SuppressLint("SimpleDateFormat")
    public String getCreationDateWithFormat(@NonNull String dateFormat) {
        return getCreationDateWithFormat(new SimpleDateFormat(dateFormat));
    }

    public String getCreationDateWithFormat() {
        return getCreationDateWithFormat("dd/MM/yyyy");
    }


    public Date getAppointDateAsDate() {
        Date date = new Date();
        try {
            date = Constants.SIMPLE_DATE_FORMAT.parse(appointDate);
        } catch (Exception e) {
            Timber.d(e);
        }
        return date;
    }

    public String getAppointDateWithFormat(@NonNull SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(getAppointDateAsDate());
    }

    @SuppressLint("SimpleDateFormat")
    public String getAppointDateWithFormat(@NonNull String dateFormat) {
        return getAppointDateWithFormat(new SimpleDateFormat(dateFormat));
    }

    public String getAppointDateWithFormat() {
        return getAppointDateWithFormat("dd/MM/yyyy");
    }


}