package com.spring.ai.example.tools.one;


import com.spring.ai.example.tools.two.FuncToolsExample;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName FuncToolsExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月10日 14:55
 */
@SpringBootTest
public class FuncToolsExampleTest {

    @Resource
    public FuncToolsExample funcToolsExample;

    @Test
    public void funcToolsExample() {
        funcToolsExample.funcToolsExample();
    }
    @Test
    public void simpleFuncToolsExample() {
        funcToolsExample.simpleFuncToolsExample();
    }


}
