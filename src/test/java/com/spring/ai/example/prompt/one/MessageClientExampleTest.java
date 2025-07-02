package com.spring.ai.example.prompt.one;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName MessageClientExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月01日 16:37
 */
@SpringBootTest
public class MessageClientExampleTest {

    @Resource
    private MessageClientExample messageClientExample;

    @Test
    public void assistantExample() {
        messageClientExample.assistantExample();
    }

    @Test
    public void toolExample() {
        messageClientExample.toolExample();
    }

}
