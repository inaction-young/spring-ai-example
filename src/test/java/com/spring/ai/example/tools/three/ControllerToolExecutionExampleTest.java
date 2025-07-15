package com.spring.ai.example.tools.three;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName ControllerToolExecutionExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月14日 14:19
 */
@SpringBootTest
public class ControllerToolExecutionExampleTest {

    @Resource
    private ControllerToolExecutionExample controllerToolExecutionExample;

    @Test
    public void test() {
        controllerToolExecutionExample.controllerToolExecution();
    }


}
