package com.spring.ai.example.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;
import com.google.common.collect.Lists;

import java.lang.reflect.Type;
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

    private static final ObjectMapper JSON_OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(SerializationFeature.INDENT_OUTPUT);

    private static final SchemaGenerator GENERATOR = new SchemaGenerator(new SchemaGeneratorConfigBuilder(
            com.github.victools.jsonschema.generator.SchemaVersion.DRAFT_2020_12,
            com.github.victools.jsonschema.generator.OptionPreset.PLAIN_JSON)
            .with(new JacksonModule(
                    JacksonOption.RESPECT_JSONPROPERTY_REQUIRED,
                    JacksonOption.RESPECT_JSONPROPERTY_ORDER))
            .with(Option.FORBIDDEN_ADDITIONAL_PROPERTIES_BY_DEFAULT)
            .build());

    /**
     * 生成 Json Schema
     * @param clz
     * @return
     */
    public static String generateSchema(Type clz) {
        JsonNode jsonNode = GENERATOR.generateSchema(clz);
        return toJsonString(jsonNode);
    }

    /**
     * 字符串解析成Java类对象
     */
    public static <T> T parseJsonObject(String json, Type type) {
        try {
            return JSON_OBJECT_MAPPER.readValue(json, JSON_OBJECT_MAPPER.constructType(type));
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException(jpe);
        }
    }

    public static <T> String toJsonString(T o) {
        try {
            return JSON_OBJECT_MAPPER.writeValueAsString(o);
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
