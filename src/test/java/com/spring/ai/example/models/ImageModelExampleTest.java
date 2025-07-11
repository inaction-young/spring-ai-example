package com.spring.ai.example.models;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName ImageModelExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月09日 14:45
 */
@SpringBootTest
public class ImageModelExampleTest {

    @Resource
    private ImageModelExample imageModelExample;

    @Test
    public void example() {
        imageModelExample.example();
    }

}
