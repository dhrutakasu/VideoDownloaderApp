package com.app.videodownloaderapp.Models;

import android.net.Uri;

public class ImageModel {
    public String imageName;
    public Uri imageUri;
    public String picturePath;

    public ImageModel(String imageName, Uri imageUri, String picturePath) {
        this.imageName = imageName;
        this.imageUri = imageUri;
        this.picturePath = picturePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "imageName='" + imageName + '\'' +
                ", imageUri=" + imageUri +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
