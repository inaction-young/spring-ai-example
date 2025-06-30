package com.spring.ai.example.advisor.three;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName MultiAdvisorClientExampleExampleTest
 * @description:
 * @author: tj
 * @date 2025年06月26日 15:20
 */
@SpringBootTest
public class MultiAdvisorClientExampleExampleTest {

    @Resource
    private MultiAdvisorClientExample multiAdvisorClientExample;

    @Test
    public void normalExecutionExampleTest() {
        multiAdvisorClientExample.normalExecutionExample();
    }
    @Test
    public void promptEnhancementExampleTest() {
        multiAdvisorClientExample.promptEnhancementExample();
    }
    @Test
    public void sensitiveFilterExampleTest() {
        multiAdvisorClientExample.sensitiveFilterExample();
    }


}
