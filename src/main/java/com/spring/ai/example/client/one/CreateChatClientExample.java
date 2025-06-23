package com.spring.ai.example.client.one;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.stereotype.Component;

/**
 * @fileName CreateChatClientExample
 * @description:
 * @author: tj
 * @date 2025年06月19日 17:58
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateChatClientExample {

    private final DeepSeekChatModel chatModel;

    private static final String SYSTEM_PROMPT = "你是一个Java专家，请帮忙解答提出的Java相关问题。";

    public void createChatClient(String userMessage) {
        // 创建一个 client
        ChatClient chatClient = ChatClient.create(chatModel);
        // 调用 deepseek
        String content = chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(userMessage)
                .call()
                .content();

        log.info("\nCreate ChatClient content -> \n{}", content);
    }

}
