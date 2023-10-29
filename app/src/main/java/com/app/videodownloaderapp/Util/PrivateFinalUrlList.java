package com.app.videodownloaderapp.Util;

import java.util.ArrayList;

public class PrivateFinalUrlList {
    private final FinalJsonPrivate PrivatefinalJsonGet;

    public PrivateFinalUrlList(FinalJsonPrivate finalJsonPrivate) {
        this.PrivatefinalJsonGet = finalJsonPrivate;
    }

    public String getCaption() {
        return this.PrivatefinalJsonGet.getItems().get(0).getCaption() != null ? this.PrivatefinalJsonGet.getItems().get(0).getCaption().getCaption() : "";
    }

    public String getHastag() {
        return this.PrivatefinalJsonGet.getItems().get(0).getCaption() != null ? this.PrivatefinalJsonGet.getItems().get(0).getCaption().getHastag() : "";
    }

    public String getPicUrl() {
        return this.PrivatefinalJsonGet.getItems().get(0).getUser().getProfile_pic_url();
    }

    public String getProfileUrl() {
        return this.PrivatefinalJsonGet.getItems().get(0).getUser().getProfole_url();
    }

    public ArrayList<String> getUrlList() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.PrivatefinalJsonGet.getItems().get(0).getCarousel_media() != null) {
            for (int i = 0; i < this.PrivatefinalJsonGet.getItems().get(0).getCarousel_media().size(); i++) {
                if (this.PrivatefinalJsonGet.getItems().get(0).getCarousel_media().get(i).getVideo_versions() != null) {
                    arrayList.add(this.PrivatefinalJsonGet.getItems().get(0).getCarousel_media().get(i).getVideo_versions().get(i).getVideoUrl());
                } else {
                    arrayList.add(this.PrivatefinalJsonGet.getItems().get(0).getCarousel_media().get(i).getImage_versions2().getCandidates().get(i).getUrl());
                }
            }
        } else if (this.PrivatefinalJsonGet.getItems().get(0).getVideo_versions() != null) {
            arrayList.add(this.PrivatefinalJsonGet.getItems().get(0).getVideo_versions().get(0).getVideoUrl());
        } else {
            arrayList.add(this.PrivatefinalJsonGet.getItems().get(0).getImage_versions2().getCandidates().get(0).getUrl());
        }
        return arrayList;
    }

    public String getUsername() {
        return this.PrivatefinalJsonGet.getItems().get(0).getUser().getUsername();
    }
}
