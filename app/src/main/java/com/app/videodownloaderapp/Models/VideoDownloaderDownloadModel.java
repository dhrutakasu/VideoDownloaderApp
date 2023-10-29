package com.app.videodownloaderapp.Models;

public class VideoDownloaderDownloadModel {
    public long downloadId;
    public long f376id;
    public String file_size;
    public String imgUrl;
    public String progress;
    public String status;

    public long getDownloadId() {
        return this.downloadId;
    }

    public String getFile_size() {
        return this.file_size;
    }

    public long getId() {
        return this.f376id;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public String getProgress() {
        return this.progress;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDownloadId(long j) {
        this.downloadId = j;
    }

    public void setFile_size(String str) {
        this.file_size = str;
    }

    public void setId(long j) {
        this.f376id = j;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public void setProgress(String str) {
        this.progress = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
