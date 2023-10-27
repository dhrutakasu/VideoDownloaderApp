package com.app.videodownloaderapp.Models;

import com.google.gson.annotations.SerializedName;

public class Hashtag {
    private int id;
    private String category;
    @SerializedName("tag_name")
    private String tagName;
    @SerializedName("tag_name_detail")
    private String tagNameDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagNameDetail() {
        return tagNameDetail;
    }

    public void setTagNameDetail(String tagNameDetail) {
        this.tagNameDetail = tagNameDetail;
    }
}
