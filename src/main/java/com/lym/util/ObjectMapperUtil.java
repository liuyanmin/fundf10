package com.lym.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 封装 ObjectMapper ，不抛出异常
 * Created by liuyanmin on 2019/10/17.
 */
@Log4j
@Component
public class ObjectMapperUtil {

    private static ObjectMapper objectMapper;

    public ObjectMapperUtil(ObjectMapper objectMapper) {
        ObjectMapperUtil.objectMapper = objectMapper;
    }

    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("object to json fail : ", e);
        }
        return "";
    }

    public static <T> T toObject(String str, Class<T> clazz) {
        try {
            return objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            log.error("string to object fail : ", e);
        }
        return null;
    }

    public static <T> T toObject(String content, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(content, typeReference);
        } catch (IOException e) {
            log.error("string to object fail : ", e);
        }
        return null;
    }
}
