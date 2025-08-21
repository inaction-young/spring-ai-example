package com.spring.ai.example.memory;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @fileName JdbcChatMemoryExample
 * @description:
 * @author: tj
 * @date 2025年08月20日 14:57
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JdbcChatMemoryExample {

    private final ChatClient jdbcChatMemoryClient;

    public String createChat() {
        // 生成会话ID
        return System.currentTimeMillis() + "";
    }

    public void chatMemoryExample(String userMsg, String cid) {
        jdbcChatMemoryClient.prompt()
                .user(userMsg)
                .advisors(spec -> spec.params(Map.of(
                        ChatMemory.CONVERSATION_ID, cid)))
                .call()
                .content();
    }
}
