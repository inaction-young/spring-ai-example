package com.spring.ai.example.client.one;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName MultiChatClientExampleTest
 * @description:
 * @author: tj
 * @date 2025年06月20日 14:18
 */
@SpringBootTest
public class MultiChatClientExampleTest {

    @Resource
    private MultiChatClientExample multiChatClientExample;

    @Test
    public void example() {
        multiChatClientExample.multiChatClient("Java 有锁为什么还需要volatile关键字？");
    }

}
