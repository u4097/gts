package ru.panmin.gtspro.data.models;

import android.os.Parcel;
import android.os.Parcelable;

public class StandardListElement implements Parcelable {

    public static final Parcelable.Creator<StandardListElement> CREATOR = new Parcelable.Creator<StandardListElement>() {
        @Override
        public StandardListElement createFromParcel(Parcel source) {
            return new StandardListElement(source);
        }

        @Override
        public StandardListElement[] newArray(int size) {
            return new StandardListElement[size];
        }
    };

    public StandardListElement() {
    }

    private StandardListElement(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

}