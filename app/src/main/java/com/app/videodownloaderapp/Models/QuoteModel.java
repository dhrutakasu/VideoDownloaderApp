package com.app.videodownloaderapp.Models;

public class QuoteModel {
    private int id;
    private int categoryId;
    private int likeQuote;
    private String quote;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getLikeQuote() {
        return likeQuote;
    }

    public void setLikeQuote(int likeQuote) {
        this.likeQuote = likeQuote;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public QuoteModel(int id, int categoryId, int likeQuote, String quote) {
        this.id = id;
        this.categoryId = categoryId;
        this.likeQuote = likeQuote;
        this.quote = quote;
    }
}
