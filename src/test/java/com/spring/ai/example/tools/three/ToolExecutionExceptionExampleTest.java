package com.spring.ai.example.tools.three;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName ToolExecutionExceptionExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月14日 16:05
 */
@SpringBootTest
public class ToolExecutionExceptionExampleTest {

    @Resource
    private ToolExecutionExceptionExample toolExecutionExceptionExample;

    @Test
    public void test() {
        toolExecutionExceptionExample.toolExecutionException(new RuntimeException("Not find `s.subject_name` by subjects"));
    }

}
