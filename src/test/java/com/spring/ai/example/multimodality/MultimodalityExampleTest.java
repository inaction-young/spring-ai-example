package com.spring.ai.example.multimodality;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName MultimodalityExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月07日 17:43
 */
@SpringBootTest
public class MultimodalityExampleTest {

    @Resource
    private MultimodalityExample multimodalityExample;

    @Test
    public void example() {
        multimodalityExample.example();
    }

}
