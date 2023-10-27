package com.app.videodownloaderapp.Models;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private List<Hashtag> hashtag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hashtag> getHashtag() {
        return hashtag;
    }

    public void setHashtag(List<Hashtag> hashtag) {
        this.hashtag = hashtag;
    }
}
