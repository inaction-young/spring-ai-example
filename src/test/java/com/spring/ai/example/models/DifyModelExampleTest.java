package com.spring.ai.example.models;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName DifyModelExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月08日 17:45
 */
@SpringBootTest
public class DifyModelExampleTest {

    @Resource
    private DifyModelExample difyModelExample;

    @Test
    public void example() {
        difyModelExample.example();
    }

}
