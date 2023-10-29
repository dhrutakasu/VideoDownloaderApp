package com.app.videodownloaderapp.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MediaType {
    private final String url;

    public MediaType(String str) {
        this.url = str;
    }

    public static List<String> extractUrls(String str) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)", 2).matcher(str);
        while (matcher.find()) {
            arrayList.add(str.substring(matcher.start(0), matcher.end(0)));
        }
        return arrayList;
    }

    public String isStory() {
        if (this.url.startsWith("https://www.instagram.com/stories/highlights") || this.url.startsWith("https://www.instagram.com/s/")) {
            return "highlight";
        }
        if (this.url.startsWith("https://instagram.com/stories/") || this.url.startsWith("https://www.instagram.com/stories/")) {
            return "story";
        }
        return (this.url.startsWith("https://www.instagram.com/reel/") || this.url.startsWith("https://www.instagram.com/tv/") || this.url.startsWith("https://www.instagram.com/p/")) ? "post" : "notValid";
    }

    public String isValidLink() {
        if (this.url.startsWith("https://www.instagram.com/reel/") || this.url.startsWith("https://www.instagram.com/tv/") || this.url.startsWith("https://www.instagram.com/p/")) {
            return "post";
        }
        if (this.url.startsWith("https://www.instagram.com/stories/highlights") || this.url.startsWith("https://www.instagram.com/s/")) {
            return "highlight";
        }
        return (this.url.startsWith("https://instagram.com/stories/") || this.url.startsWith("https://www.instagram.com/stories/")) ? "story" : "notValid";
    }
}
