package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TradePoint implements Parcelable {

    public static final Parcelable.Creator<TradePoint> CREATOR = new Parcelable.Creator<TradePoint>() {
        @Override
        public TradePoint createFromParcel(Parcel source) {
            return new TradePoint(source);
        }

        @Override
        public TradePoint[] newArray(int size) {
            return new TradePoint[size];
        }
    };

    @SerializedName("id")
    private String id;
    @SerializedName("coordinates")
    private Coordinates coordinates;
    @SerializedName("signboard")
    private Name signboard;
    @SerializedName("address")
    private Name address;
    @SerializedName("trade_network")
    private Name tradeNetwork;
    @SerializedName("trade_network_id")
    private String tradeNetworkId;
    @SerializedName("regional_office")
    private Name regionalOffice;
    @SerializedName("formats")
    private List<String> formats = new ArrayList<>();
    @SerializedName("department_id")
    private String departmentId;
    @SerializedName("clients")
    private List<Client> clients = new ArrayList<>();
    @SerializedName("times")
    private List<Time> times = new ArrayList<>();
    @SerializedName("merchandisers")
    private List<Merchandiser> merchandisers = new ArrayList<>();
    @SerializedName("promos")
    private List<Promo> promos = new ArrayList<>();
    @SerializedName("reports")
    private List<Report> reports = new ArrayList<>();
    @SerializedName("photoreports")
    private List<PhotoReport> photoreports = new ArrayList<>();
    //@SerializedName("skus") private List<Sku> skus = new ArrayList<>();
    @SerializedName("standards")
    private List<Standard> standards = new ArrayList<>();
    @SerializedName("claims")
    private List<Claim> claims = new ArrayList<>();

    public TradePoint() {
    }

    public TradePoint(String id, Coordinates coordinates, Name signboard, Name address, Name tradeNetwork, String tradeNetworkId,
                      Name regionalOffice, List<String> formats, String departmentId, List<Client> clients, List<Time> times,
                      List<Merchandiser> merchandisers, List<Promo> promos, List<Report> reports, List<PhotoReport> photoreports,
                      List<Sku> skus, List<Standard> standards, List<Claim> claims) {
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
        //this.skus = skus;
        this.standards = standards;
        this.claims = claims;
    }

    private TradePoint(Parcel in) {
        this.id = in.readString();
        this.coordinates = in.readParcelable(Coordinates.class.getClassLoader());
        this.signboard = in.readParcelable(Name.class.getClassLoader());
        this.address = in.readParcelable(Name.class.getClassLoader());
        this.tradeNetwork = in.readParcelable(Name.class.getClassLoader());
        this.tradeNetworkId = in.readString();
        this.regionalOffice = in.readParcelable(Name.class.getClassLoader());
        this.formats = in.createStringArrayList();
        this.departmentId = in.readString();
        this.clients = in.createTypedArrayList(Client.CREATOR);
        this.times = in.createTypedArrayList(Time.CREATOR);
        this.merchandisers = in.createTypedArrayList(Merchandiser.CREATOR);
        this.promos = in.createTypedArrayList(Promo.CREATOR);
        this.reports = in.createTypedArrayList(Report.CREATOR);
        this.photoreports = in.createTypedArrayList(PhotoReport.CREATOR);
        //this.skus = in.createTypedArrayList(Sku.CREATOR);
        this.standards = in.createTypedArrayList(Standard.CREATOR);
        this.claims = in.createTypedArrayList(Claim.CREATOR);
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

    public List<String> getFormats() {
        return formats;
    }

    public void setFormats(List<String> formats) {
        this.formats = formats;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }

    public List<Merchandiser> getMerchandisers() {
        return merchandisers;
    }

    public void setMerchandisers(List<Merchandiser> merchandisers) {
        this.merchandisers = merchandisers;
    }

    public List<Promo> getPromos() {
        return promos;
    }

    public void setPromos(List<Promo> promos) {
        this.promos = promos;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<PhotoReport> getPhotoreports() {
        return photoreports;
    }

    public void setPhotoreports(List<PhotoReport> photoreports) {
        this.photoreports = photoreports;
    }

    //public List<Sku> getSkus() {
    //    return skus;
    //}

    //public void setSkus(List<Sku> skus) {
    //    this.skus = skus;
    //}

    public List<Standard> getStandards() {
        return standards;
    }

    public void setStandards(List<Standard> standards) {
        this.standards = standards;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.coordinates, flags);
        dest.writeParcelable(this.signboard, flags);
        dest.writeParcelable(this.address, flags);
        dest.writeParcelable(this.tradeNetwork, flags);
        dest.writeString(this.tradeNetworkId);
        dest.writeParcelable(this.regionalOffice, flags);
        dest.writeStringList(this.formats);
        dest.writeString(this.departmentId);
        dest.writeTypedList(this.clients);
        dest.writeTypedList(this.times);
        dest.writeTypedList(this.merchandisers);
        dest.writeTypedList(this.promos);
        dest.writeTypedList(this.reports);
        dest.writeTypedList(this.photoreports);
        //dest.writeTypedList(this.skus);
        dest.writeTypedList(this.standards);
        dest.writeTypedList(this.claims);
    }

}