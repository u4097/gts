package ru.panmin.gtspro.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PhotoReport extends RealmObject {

    @PrimaryKey private String id;
    @SerializedName("client") private Client client;
    @SerializedName("trade_point_id") private String tradePointId;
    @SerializedName("date_begin") private String dateBegin;
    @SerializedName("date_finish") private String dateFinish;
    @SerializedName("filled_percent") private double filledPercent;
    @SerializedName("count_answers") private int countAnswers;
    @SerializedName("count_questions") private int countQuestions;
    @SerializedName("promo_id") private String promoId;
    @SerializedName("name") private Name name;
    @SerializedName("type") private int type;
    @SerializedName("period") private int period;
    @SerializedName("questions") private RealmList<Question> questions = new RealmList<>();

    public PhotoReport() {
    }

    public PhotoReport(String id, Client client, String tradePointId, String dateBegin, String dateFinish, double filledPercent,
                       int countAnswers, int countQuestions, String promoId, Name name, int type, int period, RealmList<Question> questions) {
        this.id = id;
        this.client = client;
        this.tradePointId = tradePointId;
        this.dateBegin = dateBegin;
        this.dateFinish = dateFinish;
        this.filledPercent = filledPercent;
        this.countAnswers = countAnswers;
        this.countQuestions = countQuestions;
        this.promoId = promoId;
        this.name = name;
        this.type = type;
        this.period = period;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTradePointId() {
        return tradePointId;
    }

    public void setTradePointId(String tradePointId) {
        this.tradePointId = tradePointId;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public double getFilledPercent() {
        return filledPercent;
    }

    public void setFilledPercent(double filledPercent) {
        this.filledPercent = filledPercent;
    }

    public int getCountAnswers() {
        return countAnswers;
    }

    public void setCountAnswers(int countAnswers) {
        this.countAnswers = countAnswers;
    }

    public int getCountQuestions() {
        return countQuestions;
    }

    public void setCountQuestions(int countQuestions) {
        this.countQuestions = countQuestions;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public RealmList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(RealmList<Question> questions) {
        this.questions = questions;
    }

}