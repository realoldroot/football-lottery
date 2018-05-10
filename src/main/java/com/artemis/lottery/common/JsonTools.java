package com.artemis.lottery.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * json转换工具
 *
 * @author zhengenshen
 * @date 2018-05-10 11:09
 */

public class JsonTools {


    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static <T> T toBean(String o, Class<T> valueType) throws IOException {
        return objectMapper.readValue(o, valueType);
    }

    public static String toJson(Object value) throws JsonProcessingException {
        return objectMapper.writeValueAsString(value);
    }
}
