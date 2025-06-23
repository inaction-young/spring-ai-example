package com.spring.ai.example.client.one;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

/**
 * @fileName MultiChatClientExample
 * @description:
 * @author: tj
 * @date 2025年06月20日 14:04
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MultiChatClientExample {

    private final ChatClient deepseekClient;

    private final ChatClient openAiClient;

    private static final String SYSTEM_PROMPT = "你是一个Java专家，请帮忙解答提出的Java相关问题。";

    public void multiChatClient(String userMessage) {
        String content = deepseekClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(userMessage)
                .call()
                .content();

        log.info("\nDeepseek result content -> \n{}", content);


        content = openAiClient.prompt()
                .user(userMessage)
                .call()
                .content();

        log.info("\nOpenAi result content -> \n{}", content);
    }


}
