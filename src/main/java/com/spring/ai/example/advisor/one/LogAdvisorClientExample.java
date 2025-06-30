package com.spring.ai.example.advisor.one;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

/**
 * @fileName LogAdvisorClientExample
 * @description:
 * @author: tj
 * @date 2025年06月25日 15:00
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LogAdvisorClientExample {

    public final ChatClient logAdvisorClient;

    private static final String SYSTEM_PROMPT = "你是一个Java专家，请帮忙解答提出的Java相关问题。";


    public void logExample() {
        String content = logAdvisorClient.prompt()
                .system(SYSTEM_PROMPT)
                .user("Java 之父的全称叫什么？")
                .call()
                .content();
        log.info("\n[call] Java 之父 -> {}", content);

        Flux<String> contentFlux = logAdvisorClient.prompt()
                .system(SYSTEM_PROMPT)
                .user("Java 之父的全称叫什么？")
                .stream()
                .content();
        content = contentFlux.collectList().block().stream().collect(Collectors.joining());
        log.info("\n[stream] Java 之父 -> {}", content);
    }

}
