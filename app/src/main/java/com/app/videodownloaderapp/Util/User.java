package com.app.videodownloaderapp.Util;


public class User {
    private String profile_pic_url;
    private String username;

    public User(String str, String str2) {
        this.username = str;
        this.profile_pic_url = str2;
    }

    public String getProfile_pic_url() {
        return this.profile_pic_url;
    }

    public String getProfole_url() {
        StringBuilder m = new StringBuilder();
        m.append("https://instagram.com/");
        m.append(this.username);
        return m.toString();
    }

    public String getUsername() {
        return this.username;
    }

    public void setProfile_pic_url(String str) {
        this.profile_pic_url = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }
}
