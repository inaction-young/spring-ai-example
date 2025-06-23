package com.spring.ai.example.client.one;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

/**
 * @fileName StreamChatClientExample
 * @description:
 * @author: tj
 * @date 2025年06月20日 17:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StreamChatClientExample {

    private final ChatClient deepseekClient;

    private static final String SYSTEM_PROMPT = "你是一个Java专家，请帮忙解答提出的Java相关问题。";

    public void streamExample(String userMessage) {
        deepseekClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(userMessage)
                // 使用 stream 模式
                .stream()
                // 返回的是Flux<String>
                .content()
                // 直接订阅后通过日志打印出来了
                .doOnNext(log::info)
                .subscribe();
    }
}
