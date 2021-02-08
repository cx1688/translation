package com.bluesky.glabol;

public class GlobalConfig {
    private static String content;
    private static String date;

    public static String getContent() {
        return content;
    }

    public static void setContent(String content) {
        GlobalConfig.content = content;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        GlobalConfig.date = date;
    }
}
