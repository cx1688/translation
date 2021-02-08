package com.bluesky.config;

public enum PictureRecognitionApi {

    T("https://hii.cn/action.php");
    private String url;
    PictureRecognitionApi(String url) {
        this.url =url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
