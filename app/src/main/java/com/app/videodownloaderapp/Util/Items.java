package com.app.videodownloaderapp.Util;

import java.util.List;

public class Items {
    private Caption caption;
    private List<Carousel_media> carousel_media;
    private Image_versions2 image_versions2;
    private User user;
    private List<Video_Versions> video_versions;

    public Items(User user2, Caption caption2, List<Video_Versions> list) {
        this.user = user2;
        this.caption = caption2;
        this.video_versions = list;
    }

    public Caption getCaption() {
        return this.caption;
    }

    public List<Carousel_media> getCarousel_media() {
        return this.carousel_media;
    }

    public Image_versions2 getImage_versions2() {
        return this.image_versions2;
    }

    public User getUser() {
        return this.user;
    }

    public List<Video_Versions> getVideo_versions() {
        return this.video_versions;
    }

    public void setCaption(Caption caption2) {
        this.caption = caption2;
    }

    public void setCarousel_media(List<Carousel_media> list) {
        this.carousel_media = list;
    }

    public void setImage_versions2(Image_versions2 image_versions22) {
        this.image_versions2 = image_versions22;
    }

    public void setUser(User user2) {
        this.user = user2;
    }

    public void setVideo_versions(List<Video_Versions> list) {
        this.video_versions = list;
    }

    public Items(User user2, Caption caption2, Image_versions2 image_versions22, List<Carousel_media> list) {
        this.user = user2;
        this.caption = caption2;
        this.image_versions2 = image_versions22;
        this.carousel_media = list;
    }
}
