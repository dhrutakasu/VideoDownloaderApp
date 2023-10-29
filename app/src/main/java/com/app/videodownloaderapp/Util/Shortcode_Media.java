package com.app.videodownloaderapp.Util;

public class Shortcode_Media {
    private String display_url;
    private InstaCaptions edge_media_to_caption;
    private Edge_sidecar_to_children edge_sidecar_to_children;
    private ProfileDetails owner;
    private String video_url;

    public Shortcode_Media(String str, String str2, InstaCaptions instaCaptions, ProfileDetails profileDetails) {
        this.display_url = str;
        this.video_url = str2;
        this.edge_media_to_caption = instaCaptions;
        this.owner = profileDetails;
    }

    public String getDisplay_url() {
        return this.display_url;
    }

    public InstaCaptions getEdge_media_to_caption() {
        return this.edge_media_to_caption;
    }

    public Edge_sidecar_to_children getEdge_sidecar_to_children() {
        return this.edge_sidecar_to_children;
    }

    public ProfileDetails getOwner() {
        return this.owner;
    }

    public String getVideo_url() {
        return this.video_url;
    }

    public void setDisplay_url(String str) {
        this.display_url = str;
    }

    public void setEdge_media_to_caption(InstaCaptions instaCaptions) {
        this.edge_media_to_caption = instaCaptions;
    }

    public void setEdge_sidecar_to_children(Edge_sidecar_to_children edge_sidecar_to_children2) {
        this.edge_sidecar_to_children = edge_sidecar_to_children2;
    }

    public void setOwner(ProfileDetails profileDetails) {
        this.owner = profileDetails;
    }

    public void setVideo_url(String str) {
        this.video_url = str;
    }

    public Shortcode_Media(String str, InstaCaptions instaCaptions, ProfileDetails profileDetails, Edge_sidecar_to_children edge_sidecar_to_children2) {
        this.video_url = str;
        this.edge_media_to_caption = instaCaptions;
        this.owner = profileDetails;
        this.edge_sidecar_to_children = edge_sidecar_to_children2;
    }
}
