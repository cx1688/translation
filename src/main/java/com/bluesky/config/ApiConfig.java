package com.bluesky.config;

public enum ApiConfig {
    GOOGLE_TRANSLATION_API("http://translate.google.cn/translate_a/single?client=gtx&dt=t&dj=1&ie=UTF-8&sl=auto&tl=zh_CN&q=calculate"),
    YOUDAO_TRANSLATION_API("http://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i={content}");

    private String url;
    ApiConfig(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
