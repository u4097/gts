package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Promo implements Parcelable {
    String id;
    String name;
    String author;
    String description;
    String sku;
    Date begin_date;
    Date finish_date;
    List<String> attachedME;
    List<String> clients;
    Boolean isActive;

    public Promo(String id,
                 String name,
                 String author,
                 String description,
                 String sku,
                 Date begin_date,
                 Date finish_date,
                 List<String> attachedME,
                 List<String> clients,
                 Boolean isActive) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.sku = sku;
        this.begin_date = begin_date;
        this.finish_date = finish_date;
        this.attachedME = attachedME;
        this.clients = clients;
        this.isActive = isActive;
    }

    public Promo() {
    }


    public static final Parcelable.Creator<Promo> CREATOR = new Parcelable.Creator<Promo>() {
        @Override
        public Promo createFromParcel(Parcel source) {
            return new Promo(source);
        }

        @Override
        public Promo[] newArray(int size) {
            return new Promo[size];
        }
    };


    private Promo(Parcel in) {
        id = in.readString();
        name = in.readString();
        author = in.readString();
        description = in.readString();
        sku = in.readString();
        begin_date = new Date(in.readLong());
        finish_date = new Date(in.readLong());
        attachedME = new ArrayList<String>();
        in.readList(attachedME, String.class.getClassLoader());
        clients = new ArrayList<String>();
        in.readList(clients, String.class.getClassLoader());
        isActive = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(author);
        dest.writeString(description);
        dest.writeString(sku);
        dest.writeLong(begin_date.getTime());
        dest.writeLong(finish_date.getTime());
        dest.writeList(attachedME);
        dest.writeList(clients);
        dest.writeByte((byte) (isActive ? 1 : 0));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    public List<String> getAttachedME() {
        return attachedME;
    }

    public void setAttachedME(List<String> attachedME) {
        this.attachedME = attachedME;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<String> getClients() {
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}