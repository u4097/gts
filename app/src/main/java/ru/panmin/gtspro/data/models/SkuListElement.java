package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku.GroupAdapter;

public class SkuListElement extends RealmObject {

    @PrimaryKey private String id;
    @SerializedName("name") private Name name;
    @SerializedName("brand") private Brand brand;
    @SerializedName("category") private Category category;
    @SerializedName("group") private Group group;
    @SerializedName("subbrand") private SubBrand subBrand;
    @SerializedName("ean") private RealmList<String> ean = new RealmList<>();
    @SerializedName("plu") private RealmList<String> plu = new RealmList<>();

    public SkuListElement() {
    }

    public SkuListElement(SkuForAdapter skuListElement) {
        id = skuListElement.getId();
        name = skuListElement.getName();
        brand = skuListElement.getBrand();
        category = skuListElement.getCategory();
        group = skuListElement.getGroup();
        subBrand = skuListElement.getSubBrand();
        ean = new RealmList<>();
        ean.addAll(skuListElement.getEan());
        plu = new RealmList<>();
        plu.addAll(skuListElement.getPlu());
    }

    public SkuListElement(String id, Name name, Brand brand, Category category, Group group, SubBrand subBrand, RealmList<String> ean, RealmList<String> plu) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.group = group;
        this.subBrand = subBrand;
        this.ean = ean;
        this.plu = plu;
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

    public RealmList<String> getEan() {
        return ean;
    }

    public void setEan(RealmList<String> ean) {
        this.ean = ean;
    }

    public RealmList<String> getPlu() {
        return plu;
    }

    public void setPlu(RealmList<String> plu) {
        this.plu = plu;
    }

}