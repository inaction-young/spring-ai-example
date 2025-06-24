package com.spring.ai.example.client.two;


import com.google.common.collect.Lists;
import com.spring.ai.example.client.record.JdkVersionResponse;
import com.spring.ai.example.utils.ConvertorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.converter.StructuredOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @fileName CallResponseExample
 * @description:
 * @author: tj
 * @date 2025年06月23日 17:11
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallResponseExample {

    private final ChatClient deepseekClient;

    private final ChatClient openAiClient;

    private static final String SYSTEM_PROMPT = "你是一个Java专家，请帮忙解答提出的Java相关问题。";


    public void chatResponse(String userMessage) {
        ChatResponse deepseekChatResponse = deepseekClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(userMessage)
                .call()
                .chatResponse();

        // 获取到输出内容
        deepseekChatResponse.getResult().getOutput().getText();
        // 元数据
        deepseekChatResponse.getMetadata();

        log.info("\nDeepseek chatResponse \n-> {}", ConvertorUtils.toJsonString(deepseekChatResponse));
        log.info("\nDeepseek chatResponse getResult \n-> {}", ConvertorUtils.toJsonString(deepseekChatResponse.getResult()));
        log.info("\nDeepseek chatResponse getMetadata \n-> {}", ConvertorUtils.toJsonString(deepseekChatResponse.getMetadata()));

        ChatResponse openAiChatResponse = openAiClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(userMessage)
                .call()
                .chatResponse();

        log.info("\nOpenAi chatResponse getResult \n-> {}", ConvertorUtils.toJsonString(openAiChatResponse.getResult()));
        log.info("\nOpenAi chatResponse getMetadata \n-> {}", ConvertorUtils.toJsonString(openAiChatResponse.getMetadata()));
    }

    public void chatClientResponse(String userMessage) {
        ChatClientResponse deepseekChatClientResponse = deepseekClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(userMessage)
                .call()
                .chatClientResponse();

        log.info("\nDeepseek chatClientResponse \n-> {}", ConvertorUtils.toJsonString(deepseekChatClientResponse));

        ChatResponse deepseekChatResponse = deepseekChatClientResponse.chatResponse();
        log.info("\nDeepseek chatResponse \n-> {}", ConvertorUtils.toJsonString(deepseekChatResponse));
        log.info("\nDeepseek chatResponse getResult \n-> {}", ConvertorUtils.toJsonString(deepseekChatResponse.getResult()));
        log.info("\nDeepseek chatResponse getMetadata \n-> {}", ConvertorUtils.toJsonString(deepseekChatResponse.getMetadata()));

        Map<String, Object> context = deepseekChatClientResponse.context();
        log.info("\nDeepseek context \n-> {}", ConvertorUtils.toJsonString(context));
    }


    public void entity() {
        JdkVersionResponse jdkVersionResponse = deepseekClient.prompt()
                .system(SYSTEM_PROMPT)
                .user("列出Java的JDK最近一个版本及其发布时间")
                .call()
                .entity(JdkVersionResponse.class);
        log.info("\nEntity jdkVersionResponse \n-> {}", ConvertorUtils.toJsonString(jdkVersionResponse));

        List<JdkVersionResponse> jdkVersionResponses = deepseekClient.prompt()
                .system(SYSTEM_PROMPT)
                .user("列出Java的JDK所有版本以及版本发布时间")
                .call()
                .entity(new ParameterizedTypeReference<List<JdkVersionResponse>>() {});

        log.info("\nEntity jdkVersionResponse list \n-> {}", ConvertorUtils.toJsonString(jdkVersionResponses));
    }

    public void structuredOutputConverter() {
        // json map
        Map<String, String> mapResponse = deepseekClient.prompt()
                .system(SYSTEM_PROMPT)
                .user("给出Java的JDK使用最广泛的一个版本及其发布时间")
                .call()
                .entity(new StructuredOutputConverter<Map<String, String>>() {
                    @Override
                    public String getFormat() {
                        return "{\"jdk_version\" : \"jdk版本\", \"jdk_release_date\" : \"jdk发布日期\"}";
                    }

                    @Override
                    public Map<String, String> convert(String source) {
                        source = source.replaceAll("```json", "");
                        source = source.replaceAll("```", "");
                        return ConvertorUtils.parseJsonObject(source, Map.class);
                    }
                });
        log.info("\nstructuredOutputConverter mapResponse \n-> {}", ConvertorUtils.toJsonString(mapResponse));

        // json list
        List<String> listResponse = deepseekClient.prompt()
                .system(SYSTEM_PROMPT)
                .user("给出Java的JDK使用最广泛的一个版本及其发布时间")
                .call()
                .entity(new StructuredOutputConverter<List<String>>() {
                    @Override
                    public String getFormat() {
                        String format = "你的响应格式必须按照下列给出格式输出。\n不需要做任何解释\n不要在响应中包含markdown代码块\n从输出中删除```json markdown\n以下是你的输出格式：\n```%s```\n";
                        return String.format(format, "[\"jdk版本\", \"jdk发布日期(YYYY_MM_DD格式)}\"]");
                    }

                    @Override
                    public List<String> convert(String source) {
                        return ConvertorUtils.parseJsonObject(source, List.class);
                    }
                });
        log.info("\nstructuredOutputConverter listResponse \n-> {}", ConvertorUtils.toJsonString(listResponse));

        // customize
        Map<String, String> customizeResponse = deepseekClient.prompt()
                .system(SYSTEM_PROMPT)
                .user("给出Java的JDK使用最广泛的一个版本及其发布时间")
                .call()
                .entity(new StructuredOutputConverter<Map<String, String>>() {
                    @Override
                    public String getFormat() {
                        String format = "你的响应格式必须按照下列给出格式输出。\n不需要做任何解释\n不要在响应中包含markdown代码块\n从输出中删除```json markdown\n以下是你的输出格式：\n```%s```\n";
                        return String.format(format, "{jdk版本} - {jdk发布日期(YY/MM/DD格式)}");
                    }

                    @Override
                    public Map<String, String> convert(String source) {
                        String[] split = source.split(" - ");
                        return new HashMap<>(){{
                            put("v", split[0]);
                            put("date", split[1]);
                        }};
                    }
                });
        log.info("\nstructuredOutputConverter customizeResponse \n-> {}", ConvertorUtils.toJsonString(customizeResponse));

    }

}
