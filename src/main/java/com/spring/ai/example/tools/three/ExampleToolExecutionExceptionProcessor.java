package com.spring.ai.example.tools.three;


import com.spring.ai.example.utils.ConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.execution.ToolExecutionException;
import org.springframework.ai.tool.execution.ToolExecutionExceptionProcessor;

import java.util.Map;

/**
 * @fileName ExampleToolExecutionExceptionProcessor
 * @description:
 * @author: tj
 * @date 2025年07月14日 16:24
 */
@Slf4j
public class ExampleToolExecutionExceptionProcessor implements ToolExecutionExceptionProcessor {
    @Override
    public String process(ToolExecutionException e) {
        log.info("\nExample tool execution exception -> {}, ", e.getMessage());
        return ConvertorUtils.toJsonString(Map.of(
                "result", "fail",
                "error", e.getMessage()));
    }
}
