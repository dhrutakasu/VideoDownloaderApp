package com.app.videodownloaderapp.Models;

public class InstaDataProvider {
    long downloadRefId;
    String downloadUrl;
    boolean isDownloadCompleted;
    boolean isVideo;
    String thumbnailUrl;
    String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return this.isVideo;
    }

    public void setVideo(boolean video) {
        this.isVideo = video;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public long getDownloadRefId() {
        return this.downloadRefId;
    }

    public void setDownloadRefId(long downloadRefId) {
        this.downloadRefId = downloadRefId;
    }

    public boolean isDownloadCompleted() {
        return this.isDownloadCompleted;
    }

    public void setDownloadCompleted(boolean downloadCompleted) {
        this.isDownloadCompleted = downloadCompleted;
    }
}
