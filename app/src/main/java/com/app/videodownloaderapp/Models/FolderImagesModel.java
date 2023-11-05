package com.app.videodownloaderapp.Models;

import android.net.Uri;

public class FolderImagesModel {
    private Uri imageUri;
    private Boolean isCroppedDone;
    private String pictureDimension, pictureName, picturePath, pictureSize;
    private Boolean selected;

    public FolderImagesModel() {
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Boolean getCroppedDone() {
        return isCroppedDone;
    }

    public void setCroppedDone(Boolean croppedDone) {
        isCroppedDone = croppedDone;
    }

    public String getPictureDimension() {
        return pictureDimension;
    }

    public void setPictureDimension(String pictureDimension) {
        this.pictureDimension = pictureDimension;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPictureSize() {
        return pictureSize;
    }

    public void setPictureSize(String pictureSize) {
        this.pictureSize = pictureSize;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "FolderImagesModel{" +
                "imageUri=" + imageUri +
                ", isCroppedDone=" + isCroppedDone +
                ", pictureDimension='" + pictureDimension + '\'' +
                ", pictureName='" + pictureName + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", pictureSize='" + pictureSize + '\'' +
                ", selected=" + selected +
                '}';
    }
}
