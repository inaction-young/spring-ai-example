package com.spring.ai.example.client.one;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName CreateChatClientExampleTest
 * @description:
 * @author: tj
 * @date 2025年06月20日 11:33
 */
@SpringBootTest
public class CreateChatClientExampleTest {

    @Resource
    private CreateChatClientExample createChatClientExample;

    @Test
    public void example() {
        createChatClientExample.createChatClient("Java为什么要使用双亲委派模型？");
    }

}
