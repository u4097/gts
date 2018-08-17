package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class SkuForAdapter implements Parcelable {

    public static final Creator<SkuForAdapter> CREATOR = new Creator<SkuForAdapter>() {
        @Override
        public SkuForAdapter createFromParcel(Parcel source) {
            return new SkuForAdapter(source);
        }

        @Override
        public SkuForAdapter[] newArray(int size) {
            return new SkuForAdapter[size];
        }
    };
    private String id;
    private Name name;
    private Brand brand;
    private Category category;
    private Group group;
    private SubBrand subBrand;
    private List<String> ean = new ArrayList<>();
    private List<String> plu = new ArrayList<>();
    private boolean cheked = false;

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

    protected SkuForAdapter(Parcel in) {
        this.id = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.brand = in.readParcelable(Brand.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.group = in.readParcelable(Group.class.getClassLoader());
        this.subBrand = in.readParcelable(SubBrand.class.getClassLoader());
        this.ean = in.createStringArrayList();
        this.plu = in.createStringArrayList();
        this.cheked = in.readByte() != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkuForAdapter that = (SkuForAdapter) o;

        if (cheked != that.cheked) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        if (subBrand != null ? !subBrand.equals(that.subBrand) : that.subBrand != null) return false;
        if (ean != null ? !ean.equals(that.ean) : that.ean != null) return false;
        return plu != null ? plu.equals(that.plu) : that.plu == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (subBrand != null ? subBrand.hashCode() : 0);
        result = 31 * result + (ean != null ? ean.hashCode() : 0);
        result = 31 * result + (plu != null ? plu.hashCode() : 0);
        result = 31 * result + (cheked ? 1 : 0);
        return result;
    }

    public boolean isCheked() {
        return cheked;
    }

    public void setCheked(boolean cheked) {
        this.cheked = cheked;
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
        dest.writeByte(this.cheked ? (byte) 1 : (byte) 0);
    }
}