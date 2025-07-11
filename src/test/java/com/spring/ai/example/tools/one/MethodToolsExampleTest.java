package com.spring.ai.example.tools.one;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName ToolsExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月10日 11:03
 */
@SpringBootTest
public class MethodToolsExampleTest {

    @Resource
    private MethodToolsExample methodToolsExample;

    @Test
    public void toolsExample() {
        methodToolsExample.toolsExample();
    }

    @Test
    public void toolsContextExample() {
        methodToolsExample.toolsContextExample();
    }

    @Test
    public void manualToolsExample() {
        methodToolsExample.manualToolsExample();
    }

    @Test
    public void simplifyManualToolsExample() {
        methodToolsExample.simplifyManualToolsExample();
    }

}
