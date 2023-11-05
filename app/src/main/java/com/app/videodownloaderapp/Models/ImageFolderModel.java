package com.app.videodownloaderapp.Models;

import android.net.Uri;

public class ImageFolderModel {
    private String FolderName, bucketId, firstPic, path;
    private int numberOfPics = 0;
    private Uri uri;

    public ImageFolderModel(String folderName, String bucketId, String firstPic, String path, Uri uri) {
        FolderName = folderName;
        this.bucketId = bucketId;
        this.firstPic = firstPic;
        this.path = path;
        this.uri = uri;
    }
    public void IncreasePic() {
        this.numberOfPics++;
    }
    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNumberOfPics() {
        return numberOfPics;
    }

    public void setNumberOfPics(int numberOfPics) {
        this.numberOfPics = numberOfPics;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
