package com.spring.ai.example.structured;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName JsonStructuredExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月04日 16:49
 */
@SpringBootTest
public class JsonStructuredExampleTest {

    @Resource
    private JsonStructuredExample jsonStructuredExample;

    @Test
    public void example() {
        jsonStructuredExample.example();
    }

    @Test
    public void exampleList() {
        jsonStructuredExample.exampleList();
    }

}
