package com.app.videodownloaderapp.Models;

public class Status {
    String id, type, string;

    public Status(String id, String type, String string) {
        this.id = id;
        this.type = type;
        this.string = string;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
