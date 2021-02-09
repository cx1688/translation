package com.bluesky.util;

import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Map;

public class HttpUtils {
    public static String get(String url,String content){
        url = url.replace("{content}",content);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                // ... handle failed request
            }
          return response.body().string();
            // ... do something with response
        } catch (IOException e) {
            // ... handle IO exception
            return null;
        }
    }

    public static String post(String url,byte[] bytes,String paramName,String fileName){
        OkHttpClient client = new OkHttpClient();
        RequestBody fileBody  = RequestBody.create(bytes,MediaType.parse("image/png"));
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(paramName, fileName, fileBody)
//                .addFormDataPart("token","9f9bac3bca7458ac57af63faf030e699")
                .build();

            Request request = new Builder().url(url).post(requestBody).build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    // ... handle failed request
                }
                return response.body().string();
                // ... do something with response

            } catch (IOException e) {
              return null;
            }
    }

    public static void main(String[] args) throws IOException {
        byte[] bytes = Files.readAllBytes(new File("/media/bluesky/Data/截图录屏_选择区域_20210208123436.png").toPath());
//        System.out.println((Map<String, Object>)JsonUtils.parseObejct(post("https://hii.cn/action.php", bytes,"mypic", LocalDateTime.now() + ".png"), Map.class));
//
        String post = post("https://up.gaitubao.net/upload", bytes, "file", "blob");
        Map<String, String> o = JsonUtils.parseObejct(post, Map.class);
        System.out.println(o);
    }

    private static  String getMd5Str(){
        return null;
    }

    public static String post(String s, Map<String, String> params) {

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        params.entrySet().forEach(t->{
            builder.add(t.getKey(),t.getValue());
        });
        Request request = new Builder().url(s).post(builder.build()).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                // ... handle failed request
            }
            return  response.body().string();
            // ... do something with response

        } catch (IOException e) {
            // ... handle IO exception
            return null;
        }
    }

    private static byte[] setParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            stringBuilder.append(entry.getKey()).append("=").append(entry.getKey()).append("&");
        }
        return stringBuilder.substring(0,stringBuilder.length()-1).getBytes();
    }
}
