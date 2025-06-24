package com.spring.ai.example.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @fileName JsonUtils
 * @description:
 * @author: tj
 * @date 2025年03月26日 20:25
 */
public class ConvertorUtils {

    public static final ObjectMapper JSON_OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static <T> String toJsonString(T o) {
        try {
            return JSON_OBJECT_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException(jpe);
        }
    }

    public static <T> T parseJsonObject(String json, Class<T> type) {
        try {
            return JSON_OBJECT_MAPPER.readValue(json, type);
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException(jpe);
        }
    }

    public static <S, T> T  convert(S source, Class<T> clz) {
        return JSON_OBJECT_MAPPER.convertValue(source, clz);
    }

    public static <S, T> List<T> converts(Collection<S> source, Class<T> clz) {
        if (Objects.isNull(source) || source.isEmpty()) {
            return Lists.newArrayList();
        }

        return source.stream().map(s -> convert(s, clz)).collect(Collectors.toList());
    }




}
