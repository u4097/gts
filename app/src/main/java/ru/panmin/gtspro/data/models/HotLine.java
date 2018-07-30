package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HotLine implements Parcelable {

    public static final Parcelable.Creator<HotLine> CREATOR = new Parcelable.Creator<HotLine>() {
        @Override
        public HotLine createFromParcel(Parcel source) {
            return new HotLine(source);
        }

        @Override
        public HotLine[] newArray(int size) {
            return new HotLine[size];
        }
    };

    @SerializedName("causes") private List<Cause> causes = new ArrayList<>();
    @SerializedName("contexts") private List<HotLineContext> contexts = new ArrayList<>();

    public HotLine() {
    }

    public HotLine(List<Cause> causes, List<HotLineContext> contexts) {
        this.causes = causes;
        this.contexts = contexts;
    }

    private HotLine(Parcel in) {
        this.causes = in.createTypedArrayList(Cause.CREATOR);
        this.contexts = in.createTypedArrayList(HotLineContext.CREATOR);
    }

    public List<Cause> getCauses() {
        return causes;
    }

    public void setCauses(List<Cause> causes) {
        this.causes = causes;
    }

    public List<HotLineContext> getContexts() {
        return contexts;
    }

    public void setContexts(List<HotLineContext> contexts) {
        this.contexts = contexts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.causes);
        dest.writeTypedList(this.contexts);
    }

}