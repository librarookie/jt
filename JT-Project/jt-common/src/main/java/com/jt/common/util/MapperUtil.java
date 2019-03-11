package com.jt.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @Author chao
 * @Date 2019/2/22 - 11:29
 * 该工具类实现 ObjectMapper 的对象的转化
 * 优化了 try-catch 方法
 */
public class MapperUtil {

    static ObjectMapper mapper = new ObjectMapper();


    // 将对象转化为 json
    public static String toJSON(Object object) {
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return json;
    }

    // 将 json 转化为对象
    public static <T> T toObject(String json,Class<?> targetClass) {
        T object = null;
        try {
            object = (T) mapper.readValue(json, targetClass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return object;
    }
}
