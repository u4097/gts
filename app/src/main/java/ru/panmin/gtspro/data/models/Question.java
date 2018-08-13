package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Question extends RealmObject {

    @PrimaryKey private String id;
    @SerializedName("type") private int type;
    @SerializedName("description") private String description;
    @SerializedName("options") private RealmList<Option> options = new RealmList<>();
    @SerializedName("name") private Name name;
    @SerializedName("limit_fractional") private String limitFractional;
    @SerializedName("limit_integer") private String limitInteger;
    @SerializedName("group_name") private Name groupName;
    @SerializedName("group_type") private int groupType;
    @SerializedName("add_type") private int addType;
    @SerializedName("answer") private Answer answer;
    @SerializedName("sku") private SkuListElement sku;

    public Question() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RealmList<Option> getOptions() {
        return options;
    }

    public void setOptions(RealmList<Option> options) {
        this.options = options;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getLimitFractional() {
        return limitFractional;
    }

    public void setLimitFractional(String limitFractional) {
        this.limitFractional = limitFractional;
    }

    public String getLimitInteger() {
        return limitInteger;
    }

    public void setLimitInteger(String limitInteger) {
        this.limitInteger = limitInteger;
    }

    public Name getGroupName() {
        return groupName;
    }

    public void setGroupName(Name groupName) {
        this.groupName = groupName;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public int getAddType() {
        return addType;
    }

    public void setAddType(int addType) {
        this.addType = addType;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public SkuListElement getSku() {
        return sku;
    }

    public void setSku(SkuListElement sku) {
        this.sku = sku;
    }

}