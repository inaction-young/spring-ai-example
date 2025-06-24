package com.spring.ai.example.client.one;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName StreamChatClientExampleTest
 * @description:
 * @author: tj
 * @date 2025年06月20日 17:39
 */
@SpringBootTest
public class StreamChatClientExampleTest {

    @Resource
    private StreamChatClientExample streamChatClientExample;

    @Test
    public void example() throws InterruptedException {
        streamChatClientExample.streamExample("Java 为什么不推荐手动调用垃圾回收？");
        Thread.sleep(1000 * 60);
    }



}
