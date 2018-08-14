package ru.panmin.gtspro.data.models;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.panmin.gtspro.utils.Constants;
import timber.log.Timber;

public class Promo extends RealmObject {

    @PrimaryKey private String id;
    @SerializedName("name") private Name name;
    @SerializedName("description") private Name description;
    @SerializedName("client") private Name client;
    @SerializedName("author") private Name author;
    @SerializedName("begin_date") private String beginDate;
    @SerializedName("finish_date") private String finishDate;
    @SerializedName("mechanics") private Mechanic mechanics;
    @SerializedName("type") private Type type;
    @SerializedName("sku_ids") private RealmList<SkuListElement> skuIds = new RealmList<>();
    @SerializedName("forms") private RealmList<FormOrReport> forms = new RealmList<>();

    public Promo() {
    }

    public Promo(String id, Name name, Name description, Name client, Name author, String beginDate, String finishDate, Mechanic mechanics,
                 Type type, RealmList<SkuListElement> skuIds, RealmList<FormOrReport> forms) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.client = client;
        this.author = author;
        this.beginDate = beginDate;
        this.finishDate = finishDate;
        this.mechanics = mechanics;
        this.type = type;
        this.skuIds = skuIds;
        this.forms = forms;
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

    public Name getDescription() {
        return description;
    }

    public void setDescription(Name description) {
        this.description = description;
    }

    public Name getClient() {
        return client;
    }

    public void setClient(Name client) {
        this.client = client;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public Mechanic getMechanics() {
        return mechanics;
    }

    public void setMechanics(Mechanic mechanics) {
        this.mechanics = mechanics;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public RealmList<SkuListElement> getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(RealmList<SkuListElement> skuIds) {
        this.skuIds = skuIds;
    }

    public RealmList<FormOrReport> getForms() {
        return forms;
    }

    public void setForms(RealmList<FormOrReport> forms) {
        this.forms = forms;
    }

    public Name getAuthor() {
        return author;
    }

    public void setAuthor(Name author) {
        this.author = author;
    }

    public Date getBeginDateAsDate() {
        Date date = new Date();
        try {
            date = Constants.SIMPLE_DATE_FORMAT.parse(beginDate);
        } catch (Exception e) {
            Timber.d(e);
        }
        return date;
    }

    public Date getFinishDateAsDate() {
        Date date = new Date();
        try {
            date = Constants.SIMPLE_DATE_FORMAT.parse(finishDate);
        } catch (Exception e) {
            Timber.d(e);
        }
        return date;
    }

    public String getBeginDateWithFormat(@NonNull SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(getBeginDateAsDate());
    }

    public String getFinishDateWithFormat(@NonNull SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(getFinishDateAsDate());
    }

    public String getBeginDateWithFormat(@NonNull String dateFormat) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(getBeginDateAsDate());
    }

    public String getFinishDateWithFormat(@NonNull String dateFormat) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(getFinishDateAsDate());
    }

}