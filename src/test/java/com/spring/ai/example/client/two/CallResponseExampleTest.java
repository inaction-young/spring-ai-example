package com.spring.ai.example.client.two;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName CallResponseExampleTest
 * @description:
 * @author: tj
 * @date 2025年06月23日 17:30
 */
@SpringBootTest
public class CallResponseExampleTest {

    @Resource
    private CallResponseExample callChatResponseExample;

    @Test
    public void chatResponse() {
        callChatResponseExample.chatResponse("Jvm为什么要给内存分代？");
    }

    @Test
    public void chatClientResponse() {
        callChatResponseExample.chatClientResponse("Jvm为什么要给内存分代？");
    }

    @Test
    public void entity() {
        callChatResponseExample.entity();
    }

    @Test
    public void structuredOutputConverter() {
        callChatResponseExample.structuredOutputConverter();
    }


}
