package com.spring.ai.example.models;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

/**
 * @fileName DifyModelExample
 * @description:
 * @author: tj
 * @date 2025年07月08日 17:42
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DifyModelExample {

    private final ChatClient difyChatClient;

    public void example() {
        ChatResponse chatResponse = difyChatClient.prompt()
                .user("你谁啊？")
                .call()
                .chatResponse();
    }

}
