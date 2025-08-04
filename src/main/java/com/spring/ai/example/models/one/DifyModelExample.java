package com.spring.ai.example.models.one;


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

    private final DifyChatModel difyChatModel;

    public void modelExample() {
        String call = difyChatModel.call("你好啊！");
        log.info("\n modelExample call -> \n{}", call);
    }

    private final ChatClient difyChatClient;

public void clientExample() {
    String user = "DEFAULT-USER";
    ChatResponse chatResponse = difyChatClient.prompt()
            // 初次交互没有回话ID
            .options(DifyChatOptions.builder()
                    .user(user)
                    .build())
            .user("你谁啊？")
            .call()
            .chatResponse();

    Object conversationId = chatResponse.getResult().getMetadata().get("CONVERSATION_ID");

    chatResponse = difyChatClient.prompt()
            .options(DifyChatOptions.builder()
                    // 使用返回的回话ID
                    .conversationId(conversationId.toString())
                    .user(user)
                    .build())
            .user("你主人在干什么啊？")
            .call()
            .chatResponse();
}

}
