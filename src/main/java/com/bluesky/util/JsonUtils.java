package com.bluesky.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T parseObejct(String content, Class clazz) throws JsonProcessingException {
       return (T) objectMapper.readValue(content,clazz);
    }

    public static  <T> String parseStr(T t) throws JsonProcessingException {
        return objectMapper.writeValueAsString(t);
    }
}
