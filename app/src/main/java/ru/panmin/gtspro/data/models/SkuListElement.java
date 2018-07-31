package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SkuListElement implements Parcelable {

    public static final Parcelable.Creator<SkuListElement> CREATOR = new Parcelable.Creator<SkuListElement>() {
        @Override
        public SkuListElement createFromParcel(Parcel source) {
            return new SkuListElement(source);
        }

        @Override
        public SkuListElement[] newArray(int size) {
            return new SkuListElement[size];
        }
    };

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private Name name;
    @SerializedName("brand")
    private Brand brand;
    @SerializedName("category")
    private Category category;
    @SerializedName("group")
    private Group group;
    @SerializedName("subbrand")
    private SubBrand subBrand;
    @SerializedName("ean")
    private List<String> ean = new ArrayList<>();
    @SerializedName("plu")
    private List<String> plu = new ArrayList<>();

    public SkuListElement() {
    }

    public SkuListElement(String id, Name name, Brand brand, Category category, Group group, SubBrand subBrand, List<String> ean, List<String> plu) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.group = group;
        this.subBrand = subBrand;
        this.ean = ean;
        this.plu = plu;
    }

    protected SkuListElement(Parcel in) {
        this.id = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.brand = in.readParcelable(Brand.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.group = in.readParcelable(Group.class.getClassLoader());
        this.subBrand = in.readParcelable(SubBrand.class.getClassLoader());
        this.ean = in.createStringArrayList();
        this.plu = in.createStringArrayList();
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public SubBrand getSubBrand() {
        return subBrand;
    }

    public void setSubBrand(SubBrand subBrand) {
        this.subBrand = subBrand;
    }

    public List<String> getEan() {
        return ean;
    }

    public void setEan(List<String> ean) {
        this.ean = ean;
    }

    public List<String> getPlu() {
        return plu;
    }

    public void setPlu(List<String> plu) {
        this.plu = plu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.name, flags);
        dest.writeParcelable(this.brand, flags);
        dest.writeParcelable(this.category, flags);
        dest.writeParcelable(this.group, flags);
        dest.writeParcelable(this.subBrand, flags);
        dest.writeStringList(this.ean);
        dest.writeStringList(this.plu);
    }

}