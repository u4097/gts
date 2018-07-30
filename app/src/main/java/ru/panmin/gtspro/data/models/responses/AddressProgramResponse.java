package ru.panmin.gtspro.data.models.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ru.panmin.gtspro.data.models.HotLine;
import ru.panmin.gtspro.data.models.TradePoint;

public class AddressProgramResponse extends BaseResponse implements Parcelable {

    public static final Parcelable.Creator<AddressProgramResponse> CREATOR = new Parcelable.Creator<AddressProgramResponse>() {
        @Override
        public AddressProgramResponse createFromParcel(Parcel source) {
            return new AddressProgramResponse(source);
        }

        @Override
        public AddressProgramResponse[] newArray(int size) {
            return new AddressProgramResponse[size];
        }
    };

    @SerializedName("auto_checkout_time") private int autoCheckoutTime;
    @SerializedName("trade_point_radius") private int tradePointRadius;
    @SerializedName("hot_line") private HotLine hotLine;
    @SerializedName("trade_points") private List<TradePoint> tradePoints = new ArrayList<>();

    public AddressProgramResponse() {
    }

    public AddressProgramResponse(int autoCheckoutTime, int tradePointRadius, HotLine hotLine, List<TradePoint> tradePoints) {
        this.autoCheckoutTime = autoCheckoutTime;
        this.tradePointRadius = tradePointRadius;
        this.hotLine = hotLine;
        this.tradePoints = tradePoints;
    }

    private AddressProgramResponse(Parcel in) {
        this.autoCheckoutTime = in.readInt();
        this.tradePointRadius = in.readInt();
        this.hotLine = in.readParcelable(HotLine.class.getClassLoader());
        this.tradePoints = in.createTypedArrayList(TradePoint.CREATOR);
    }

    public int getAutoCheckoutTime() {
        return autoCheckoutTime;
    }

    public void setAutoCheckoutTime(int autoCheckoutTime) {
        this.autoCheckoutTime = autoCheckoutTime;
    }

    public int getTradePointRadius() {
        return tradePointRadius;
    }

    public void setTradePointRadius(int tradePointRadius) {
        this.tradePointRadius = tradePointRadius;
    }

    public HotLine getHotLine() {
        return hotLine;
    }

    public void setHotLine(HotLine hotLine) {
        this.hotLine = hotLine;
    }

    public List<TradePoint> getTradePoints() {
        return tradePoints;
    }

    public void setTradePoints(List<TradePoint> tradePoints) {
        this.tradePoints = tradePoints;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.autoCheckoutTime);
        dest.writeInt(this.tradePointRadius);
        dest.writeParcelable(this.hotLine, flags);
        dest.writeTypedList(this.tradePoints);
    }

}