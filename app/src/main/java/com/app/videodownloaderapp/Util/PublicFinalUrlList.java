package com.app.videodownloaderapp.Util;

import java.util.ArrayList;

public class PublicFinalUrlList {
    private FinalJsonGet PublicfinalJsonGet;

    public PublicFinalUrlList(FinalJsonGet finalJsonGet) {
        this.PublicfinalJsonGet = finalJsonGet;
    }

    public String getCaption() {
        return this.PublicfinalJsonGet.getGraphql().getShortcode_media().getEdge_media_to_caption().getEdges().size() != 0 ? this.PublicfinalJsonGet.getGraphql().getShortcode_media().getEdge_media_to_caption().getEdges().get(0).getNode().getCaption() : "";
    }

    public String getHastag() {
        return this.PublicfinalJsonGet.getGraphql().getShortcode_media().getEdge_media_to_caption().getEdges().size() != 0 ? this.PublicfinalJsonGet.getGraphql().getShortcode_media().getEdge_media_to_caption().getEdges().get(0).getNode().getHastag() : "";
    }

    public String getPicUrl() {
        return this.PublicfinalJsonGet.getGraphql().getShortcode_media().getOwner().getProfile_pic_url();
    }

    public String getProfileUrl() {
        return this.PublicfinalJsonGet.getGraphql().getShortcode_media().getOwner().getProfole_url();
    }

    public ArrayList<String> getUrlList() {
        ArrayList<String> arrayList = new ArrayList<>();
        System.out.println("------ PublicfinalJsonGet 111: " + PublicfinalJsonGet.getGraphql().toString());
        if (this.PublicfinalJsonGet.getGraphql().getShortcode_media().getEdge_sidecar_to_children() != null) {
            for (int i = 0; i < this.PublicfinalJsonGet.getGraphql().getShortcode_media().getEdge_sidecar_to_children().getImageCount(); i++) {
                if (this.PublicfinalJsonGet.getGraphql().getShortcode_media().getEdge_sidecar_to_children().getEdges().get(i).getNode().getVideo_url() != null) {
                    arrayList.add(this.PublicfinalJsonGet.getGraphql().getShortcode_media().getEdge_sidecar_to_children().getEdges().get(i).getNode().getVideo_url());
                } else {
                    arrayList.add(this.PublicfinalJsonGet.getGraphql().getShortcode_media().getEdge_sidecar_to_children().getEdges().get(i).getNode().getDisplay_url());
                }
            }
        } else if (this.PublicfinalJsonGet.getGraphql().getShortcode_media().getVideo_url() != null) {
            arrayList.add(this.PublicfinalJsonGet.getGraphql().getShortcode_media().getVideo_url());
        } else {
            arrayList.add(this.PublicfinalJsonGet.getGraphql().getShortcode_media().getDisplay_url());
        }
        return arrayList;
    }

    public String getUsername() {
        return this.PublicfinalJsonGet.getGraphql().getShortcode_media().getOwner().getUsername();
    }
}
