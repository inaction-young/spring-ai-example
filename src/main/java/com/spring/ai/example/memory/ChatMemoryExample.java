package com.spring.ai.example.memory;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @fileName ChatMemoryExample
 * @description:
 * @author: tj
 * @date 2025年07月16日 14:36
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ChatMemoryExample {

    private final ChatClient chatMemoryClient;
    private final String system = """
                        You are an assistant and need to proactively greet users.
                        The user information is as follows:
                        ```nickname: %s```
                        """;
    public String createChat() {
        // 生成一个huihuaID
        String cid = UUID.randomUUID().toString();
        chatMemoryClient.prompt()
                .system(String.format(system, "Jan"))
                // 将会话ID添加到 advisor context
                .advisors(spec -> spec.params(Map.of(
                        ChatMemory.CONVERSATION_ID, cid)))
                .call()
                .content();

        return cid;
    }

    public void chatMemoryExample(String userMsg, String cid) {
        chatMemoryClient.prompt()
                .user(userMsg)
                .advisors(spec -> spec.params(Map.of(
                        ChatMemory.CONVERSATION_ID, cid)))
                .call()
                .content();
    }
}
