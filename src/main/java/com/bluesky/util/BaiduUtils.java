package com.bluesky.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class BaiduUtils {
    private static String grant_type = "client_credentials";
    private static String client_id = "8EFRS4ULFrkOaz8heVqbXIt8";
    private static String client_secret = "4QWGwRLXYkMKyj3DaYyEsPsBo3kbo0tm";
    private static String token_url = "https://aip.baidubce.com/oauth/2.0/token";
    private static TokenInfo tokenInfo;
    private static String api = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";

    public static void main(String[] args) throws IOException {
        Map<String, Object> translation = translation(Files.readAllBytes(new File("/media/bluesky/Data/截图录屏_选择区域_20210209111018.png").toPath()));
        System.out.println(translation);
    }
    public static Map<String, Object> translation(byte[]bytes) throws IOException {
        File file = new File(".token.json");
        String rs = new String(file.exists() ? Files.readAllBytes(file.toPath()) : new byte[0]);
        if (StringUtils.isNotBlank(rs)) {
            TokenInfo tokenInfo = JsonUtils.parseObejct(rs, TokenInfo.class);
            if (StringUtils.isNotBlank(tokenInfo.getToken())) {
                LocalDateTime parse = LocalDateTime.parse(tokenInfo.getStartDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                int startSeconds = parse.getSecond();
                int currentSeconds = LocalDateTime.now().getSecond();
                if (startSeconds + tokenInfo.getExpiration() > currentSeconds) {
                    return imageToFont(tokenInfo.getToken(), bytes);
                } else {
                    tokenInfo = refreshToken();
                }
            }
        } else {
            tokenInfo = refreshToken();
        }
        return null;
    }
    private static TokenInfo refreshToken() throws JsonProcessingException {
        Map<String, Object> token = getToken();
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setStartDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Integer expires_in = (Integer) token.get("expires_in");
        tokenInfo.setExpiration(Integer.valueOf(expires_in));
        tokenInfo.setToken((String) token.get("access_token"));
        try {
            String jsonResult = JsonUtils.parseStr(tokenInfo);
            Files.write(new File(".token.json").toPath(), jsonResult.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokenInfo;

    }
    private static Map<String, Object> getToken() throws JsonProcessingException {
        Map<String, String> params = new HashMap();
        params.put("grant_type", grant_type);
        params.put("client_id", client_id);
        params.put("client_secret", client_secret);
        return JsonUtils.parseObejct(HttpUtils.post(token_url, params), Map.class);
    }

    private static Map<String, Object> imageToFont(String asscess_token, byte[] bytes) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
        ImageIO.write(bufferedImage,"jpeg",bos);
        String content = Base64.getEncoder().encodeToString(bos.toByteArray());
        Map<String, String> params = new HashMap<>();
        params.put("image", content);
        String post = HttpUtils.post(api + "?access_token=" + asscess_token, params);
        return JsonUtils.parseObejct(post, Map.class);
    }
}

class TokenInfo {
    private String startDateTime;
    private Integer expiration;
    private String token;



    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}