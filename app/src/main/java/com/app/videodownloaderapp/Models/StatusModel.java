package com.app.videodownloaderapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class StatusModel implements Parcelable {
    public static final Creator<StatusModel> CREATOR = new Creator<StatusModel>() {
        public StatusModel createFromParcel(Parcel parcel) {
            return new StatusModel(parcel);
        }

        public StatusModel[] newArray(int i) {
            return new StatusModel[i];
        }
    };
    private String filepath;
    public boolean selected = false;

    public StatusModel(String str) {
        this.filepath = str;
    }

    public int describeContents() {
        return 0;
    }

    public String getFilePath() {
        return this.filepath;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setFilePath(String str) {
        this.filepath = str;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.filepath);
    }

    public StatusModel(Parcel parcel) {
        this.filepath = parcel.readString();
    }
}
