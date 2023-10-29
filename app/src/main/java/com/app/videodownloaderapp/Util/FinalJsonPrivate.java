package com.app.videodownloaderapp.Util;

import java.util.List;

public class FinalJsonPrivate {
    private List<Items> items;

    public FinalJsonPrivate(List<Items> list) {
        this.items = list;
    }

    public List<Items> getItems() {
        return this.items;
    }

    public int getitemSize() {
        return this.items.size();
    }

    public void setItems(List<Items> list) {
        this.items = list;
    }
}
