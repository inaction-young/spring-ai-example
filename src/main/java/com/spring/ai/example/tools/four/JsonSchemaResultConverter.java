package com.spring.ai.example.tools.four;


import com.spring.ai.example.utils.ConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.execution.ToolCallResultConverter;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @fileName JsonSchemaResultConverter
 * @description:
 * @author: tj
 * @date 2025年07月15日 14:53
 */
@Slf4j
public class JsonSchemaResultConverter implements ToolCallResultConverter {
    @Override
    public String convert(Object result, Type returnType) {
        Map<String, String> data = Map.of("data", ConvertorUtils.toJsonString(result), "dataJsonSchema", ConvertorUtils.generateSchema(returnType));
//        log.info("[JsonSchemaResultConverter] convert data -> {}", data);
        return ConvertorUtils.toJsonString(data);
    }
}
