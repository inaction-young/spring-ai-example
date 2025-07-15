package com.spring.ai.example.tools.two;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName BeanFuncToolsExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月10日 15:57
 */
@SpringBootTest
public class BeanFuncToolsExampleTest {

    @Resource
    public BeanFuncToolsExample beanFuncToolsExample;

    @Test
    public void beanRmbToolsExample() {
        beanFuncToolsExample.beanRmbToolsExample();
    }
    @Test
    public void beanDataToolsExample() {
        beanFuncToolsExample.beanDataToolsExample();
    }


}
