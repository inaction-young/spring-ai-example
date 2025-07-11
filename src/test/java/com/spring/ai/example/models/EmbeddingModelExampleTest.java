package com.spring.ai.example.models;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName EmbeddingModelExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月09日 15:00
 */
@SpringBootTest
public class EmbeddingModelExampleTest {

    @Resource
    private EmbeddingModelExample embeddingModelExample;

    @Test
    public void example() {
        embeddingModelExample.example();
    }

}
