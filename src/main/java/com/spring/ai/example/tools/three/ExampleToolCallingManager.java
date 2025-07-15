package com.spring.ai.example.tools.three;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.model.tool.ToolExecutionResult;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @fileName ExampleToolCallingManager
 * @description:
 * @author: tj
 * @date 2025年07月14日 13:55
 */
@Slf4j
@Component
public class ExampleToolCallingManager implements ToolCallingManager {

    // 模拟一个自定义的 ToolCallingManager，实际使用的 DefaultToolCallingManager
    private final ToolCallingManager toolCallingManager = ToolCallingManager
            .builder()
            // 注释掉 ExampleToolExecutionExceptionProcessor，影响其他案例
            // 添加 ExampleToolExecutionExceptionProcessor 处理 ToolExecutionException
//            .toolExecutionExceptionProcessor(new ExampleToolExecutionExceptionProcessor())
            .build();

    @Override
    public List<ToolDefinition> resolveToolDefinitions(ToolCallingChatOptions chatOptions) {
        return toolCallingManager.resolveToolDefinitions(chatOptions);
    }

    @Override
    public ToolExecutionResult executeToolCalls(Prompt prompt, ChatResponse chatResponse) {
        log.info("\nExample tool calling manager!");
        return toolCallingManager.executeToolCalls(prompt, chatResponse);
    }
}
