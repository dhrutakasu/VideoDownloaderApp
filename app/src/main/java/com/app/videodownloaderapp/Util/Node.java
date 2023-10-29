package com.app.videodownloaderapp.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Node {
    private String text;

    public Node(String str) {
        this.text = str;
    }

    public String getCaption() {
        return this.text;
    }

    public String getHastag() {
        Matcher matcher = Pattern.compile("#(\\w+)").matcher(this.text);
        String str = "";
        while (matcher.find()) {
            StringBuilder m = new StringBuilder();
            m.append(str+"#");
            m.append(matcher.group(1));
            m.append(" ");
            str = m.toString();
        }
        return str;
    }

    public void setText(String str) {
        this.text = str;
    }
}
