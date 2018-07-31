package ru.panmin.gtspro.data.models.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import ru.panmin.gtspro.data.models.Error;

public class BaseResponse implements Parcelable {


    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel source) {
            return new BaseResponse(source);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };

    @SerializedName("errors") private Error errors;
    @SerializedName("status_code") private int statusCode;
    @SerializedName("is_error") private boolean error;

    public BaseResponse() {
    }

    public BaseResponse(Error errors, int statusCode, boolean error) {
        this.errors = errors;
        this.statusCode = statusCode;
        this.error = error;
    }

    BaseResponse(Parcel in) {
        this.errors = in.readParcelable(Error.class.getClassLoader());
        this.statusCode = in.readInt();
        this.error = in.readByte() != 0;
    }

    public Error getErrors() {
        return errors;
    }

    public void setErrors(Error errors) {
        this.errors = errors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.errors, flags);
        dest.writeInt(this.statusCode);
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
    }

}