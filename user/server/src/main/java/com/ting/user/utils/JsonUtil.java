package com.ting.user.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-06-11
 * Time: 下午7:39
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 转换为json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转对象
     * */
    public static <T> T fromJson(String str, Class<T> clazz){
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * json转对象
     * */
    public static <T> T fromJson(String str, TypeReference typeReference){
        try {
            return objectMapper.readValue(str,typeReference);
        } catch (IOException e) {
            return null;
        }
    }


}
