package com.spring.ai.example.structured;


import com.spring.ai.example.utils.ConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.converter.StructuredOutputConverter;
import org.springframework.core.ParameterizedTypeReference;

/**
 * @fileName JsonStructuredConverter
 * @description:
 * @author: tj
 * @date 2025年07月04日 15:04
 */
@Slf4j
public class JsonStructuredConverter<T> implements StructuredOutputConverter<T> {
    /** 输出格式的要求提示词模版 */
    private final String FORMAT_TEMPLATE = """
            你的响应格式必须是JSON格式。
            不用做任何解释，只提供符合RFC8259的JSON响应。
            不要在响应中包含markdown代码块，且从输出中删除``json markdown。
            必须遵守以上要求，不可以有任何偏差。
            下列是你输出必须遵循的JSON Schema实例：
            ```%s```
            """;
    /** Java 的类型 */
    private ParameterizedTypeReference<T> reference;

    public JsonStructuredConverter(Class<T> clz) {
        this.reference = ParameterizedTypeReference.forType(clz);
    }

    public JsonStructuredConverter(ParameterizedTypeReference<T> reference) {
        this.reference = reference;
    }

    @Override
    public String getFormat() {
        String schema = ConvertorUtils.generateSchema(reference.getType());
        log.info("\ngetFormat schema -> {}", schema);
        // 生成带有 Json Schema 的输出格式提示词
        return String.format(FORMAT_TEMPLATE, schema);
    }

    @Override
    public T convert(String text) {
        log.info("\nconvert text -> {}", text);
        // 将AI响应输出的文本转换成Java类
        return ConvertorUtils.parseJsonObject(text.trim(), reference.getType());
    }
}
