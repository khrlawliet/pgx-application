package com.kaibaltech.pgx.reg.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaibaltech.pgx.reg.service.exception.JsonProcessingRuntimeException;

public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonProcessingRuntimeException("Failed to convert object to JSON", e);
        }
    }
}