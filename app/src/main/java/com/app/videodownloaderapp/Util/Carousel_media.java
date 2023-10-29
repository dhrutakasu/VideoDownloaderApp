package com.app.videodownloaderapp.Util;

import java.util.List;

public class Carousel_media {
    private Image_versions2 image_versions2;
    private List<Video_Versions> video_versions;

    public Carousel_media(Image_versions2 image_versions22, List<Video_Versions> list) {
        this.image_versions2 = image_versions22;
        this.video_versions = list;
    }

    public Image_versions2 getImage_versions2() {
        return this.image_versions2;
    }

    public List<Video_Versions> getVideo_versions() {
        return this.video_versions;
    }

    public void setImage_versions2(Image_versions2 image_versions22) {
        this.image_versions2 = image_versions22;
    }

    public void setVideo_versions(List<Video_Versions> list) {
        this.video_versions = list;
    }
}
