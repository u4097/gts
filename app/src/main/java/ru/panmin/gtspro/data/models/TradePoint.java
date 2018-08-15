package ru.panmin.gtspro.data.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import ru.panmin.gtspro.utils.Constants;
import timber.log.Timber;

public class TradePoint extends RealmObject {

    @PrimaryKey private String id;
    @SerializedName("coordinates") private Coordinates coordinates;
    @SerializedName("signboard") private Name signboard;
    @SerializedName("address") private Name address;
    @SerializedName("trade_network") private Name tradeNetwork;
    @SerializedName("trade_network_id") private String tradeNetworkId;
    @SerializedName("regional_office") private Name regionalOffice;
    @SerializedName("formats") private RealmList<String> formats = new RealmList<>();
    @SerializedName("department_id") private String departmentId;
    @SerializedName("clients") private RealmList<Client> clients = new RealmList<>();
    @SerializedName("times") private RealmList<Time> times = new RealmList<>();
    @SerializedName("merchandisers") private RealmList<Merchandiser> merchandisers = new RealmList<>();
    @SerializedName("promos") private RealmList<Promo> promos = new RealmList<>();
    @SerializedName("reports") private RealmList<FormOrReport> reports = new RealmList<>();
    @SerializedName("photoreports") private RealmList<FormOrReport> photoreports = new RealmList<>();
    @SerializedName("skus") private RealmList<Sku> skus = new RealmList<>();
    @SerializedName("standards") private RealmList<Standard> standards = new RealmList<>();
    @SerializedName("claims") private RealmList<Claim> claims = new RealmList<>();
    @Ignore private double distance = 0.0d;

    public TradePoint() {
    }

    public TradePoint(String id, Coordinates coordinates, Name signboard, Name address, Name tradeNetwork, String tradeNetworkId,
                      Name regionalOffice, RealmList<String> formats, String departmentId, RealmList<Client> clients, RealmList<Time> times,
                      RealmList<Merchandiser> merchandisers, RealmList<Promo> promos, RealmList<FormOrReport> reports, RealmList<FormOrReport> photoreports,
                      RealmList<Sku> skus, RealmList<Standard> standards, RealmList<Claim> claims) {
        this.id = id;
        this.coordinates = coordinates;
        this.signboard = signboard;
        this.address = address;
        this.tradeNetwork = tradeNetwork;
        this.tradeNetworkId = tradeNetworkId;
        this.regionalOffice = regionalOffice;
        this.formats = formats;
        this.departmentId = departmentId;
        this.clients = clients;
        this.times = times;
        this.merchandisers = merchandisers;
        this.promos = promos;
        this.reports = reports;
        this.photoreports = photoreports;
        this.skus = skus;
        this.standards = standards;
        this.claims = claims;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Name getSignboard() {
        return signboard;
    }

    public void setSignboard(Name signboard) {
        this.signboard = signboard;
    }

    public Name getAddress() {
        return address;
    }

    public void setAddress(Name address) {
        this.address = address;
    }

    public Name getTradeNetwork() {
        return tradeNetwork;
    }

    public void setTradeNetwork(Name tradeNetwork) {
        this.tradeNetwork = tradeNetwork;
    }

    public String getTradeNetworkId() {
        return tradeNetworkId;
    }

    public void setTradeNetworkId(String tradeNetworkId) {
        this.tradeNetworkId = tradeNetworkId;
    }

    public Name getRegionalOffice() {
        return regionalOffice;
    }

    public void setRegionalOffice(Name regionalOffice) {
        this.regionalOffice = regionalOffice;
    }

    public RealmList<String> getFormats() {
        return formats;
    }

    public void setFormats(RealmList<String> formats) {
        this.formats = formats;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public RealmList<Client> getClients() {
        return clients;
    }

    public void setClients(RealmList<Client> clients) {
        this.clients = clients;
    }

    public RealmList<Time> getTimes() {
        return times;
    }

    public void setTimes(RealmList<Time> times) {
        this.times = times;
    }

    public RealmList<Merchandiser> getMerchandisers() {
        return merchandisers;
    }

    public void setMerchandisers(RealmList<Merchandiser> merchandisers) {
        this.merchandisers = merchandisers;
    }

    public RealmList<Promo> getPromos() {
        return promos;
    }

    public void setPromos(RealmList<Promo> promos) {
        this.promos = promos;
    }

    public RealmList<FormOrReport> getReports() {
        return reports;
    }

    public void setReports(RealmList<FormOrReport> reports) {
        this.reports = reports;
    }

    public RealmList<FormOrReport> getPhotoreports() {
        return photoreports;
    }

    public void setPhotoreports(RealmList<FormOrReport> photoreports) {
        this.photoreports = photoreports;
    }

    public RealmList<Sku> getSkus() {
        return skus;
    }

    public void setSkus(RealmList<Sku> skus) {
        this.skus = skus;
    }

    public RealmList<Standard> getStandards() {
        return standards;
    }

    public void setStandards(RealmList<Standard> standards) {
        this.standards = standards;
    }

    public RealmList<Claim> getClaims() {
        return claims;
    }

    public void setClaims(RealmList<Claim> claims) {
        this.claims = claims;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}