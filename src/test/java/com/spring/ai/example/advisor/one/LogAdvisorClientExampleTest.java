package com.spring.ai.example.advisor.one;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName LogAdvisorClientExampleTest
 * @description:
 * @author: tj
 * @date 2025年06月25日 15:09
 */
@SpringBootTest
public class LogAdvisorClientExampleTest {

    @Resource
    private LogAdvisorClientExample logAdvisorClientExample;

    @Test
    public void logExample() {
        logAdvisorClientExample.logExample();
    }

}
