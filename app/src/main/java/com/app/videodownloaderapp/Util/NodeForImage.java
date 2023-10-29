package com.app.videodownloaderapp.Util;

public class NodeForImage {
    private String display_url;
    private String video_url;

    public NodeForImage(String str, String str2) {
        this.display_url = str;
        this.video_url = str2;
    }

    public String getDisplay_url() {
        return this.display_url;
    }

    public String getVideo_url() {
        return this.video_url;
    }

    public void setDisplay_url(String str) {
        this.display_url = str;
    }

    public void setVideo_url(String str) {
        this.video_url = str;
    }
}
