package com.spring.ai.example.tools.three;


import org.springframework.ai.tool.execution.ToolExecutionExceptionProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @fileName ToolCallingManagerConfiguration
 * @description:
 * @author: tj
 * @date 2025年07月14日 14:22
 */
@Configuration
public class ToolCallingManagerConfiguration {

//    @Bean
    public ExampleToolCallingManager  exampleToolCallingManager() {
        return new ExampleToolCallingManager();
    }

//    @Bean
    public ToolExecutionExceptionProcessor exampleToolExecutionExceptionProcessor() {
        return new ExampleToolExecutionExceptionProcessor();
    }

}
