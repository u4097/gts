package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class SkuForAdapter implements Parcelable {

    private String id;
    private Name name;
    private Brand brand;
    private Category category;
    private Group group;
    private SubBrand subBrand;
    private List<String> ean = new ArrayList<>();
    private List<String> plu = new ArrayList<>();

    public SkuForAdapter() {
    }

    public SkuForAdapter(SkuListElement skuListElement) {
        id = skuListElement.getId();
        name = skuListElement.getName();
        brand = skuListElement.getBrand();
        category = skuListElement.getCategory();
        group = skuListElement.getGroup();
        subBrand = skuListElement.getSubBrand();
        ean = skuListElement.getEan();
        plu = skuListElement.getPlu();
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
        dest.writeString(id);
        dest.writeParcelable(name, flags);
        dest.writeParcelable(brand, flags);
        dest.writeParcelable(category, flags);
        dest.writeParcelable(group, flags);
        dest.writeParcelable(subBrand, flags);
        dest.writeStringList(this.ean);
        dest.writeStringList(this.plu);
    }

    protected SkuForAdapter(Parcel in) {
        this.id = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.brand = in.readParcelable(Brand.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.group = in.readParcelable(Group.class.getClassLoader());
        this.subBrand = in.readParcelable(SubBrand.class.getClassLoader());
        this.ean = in.createStringArrayList();
        this.plu = in.createStringArrayList();
    }

    public  final Creator<SkuForAdapter> CREATOR = new Creator<SkuForAdapter>() {
        @Override
        public SkuForAdapter createFromParcel(Parcel source) {
            return new SkuForAdapter(source);
        }

        @Override
        public SkuForAdapter[] newArray(int size) {
            return new SkuForAdapter[size];
        }
    };

}