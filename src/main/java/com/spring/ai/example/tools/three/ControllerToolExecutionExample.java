package com.spring.ai.example.tools.three;


import com.spring.ai.example.tools.one.MockWeatherTools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.model.tool.ToolExecutionResult;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.stereotype.Component;

/**
 * @fileName ControllerToolExecutionExample
 * @description:
 * @author: tj
 * @date 2025年07月14日 13:53
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ControllerToolExecutionExample {

    private final DeepSeekChatModel chatModel;

    private final ExampleToolCallingManager exampleToolCallingManager;


    public void controllerToolExecution() {
        // 传入给 ChatModel 的一些选项
        ToolCallingChatOptions options = ToolCallingChatOptions.builder()
                // 关闭自动控制工具执行
                .internalToolExecutionEnabled(false)
                // 可以通过 ToolCallingChatOptions 添加工具
                .toolCallbacks(ToolCallbacks.from(new MockWeatherTools()))
                .build();

        Prompt prompt = Prompt.builder()
                // 提示词
                .messages(SystemMessage.builder()
                        .text("气温超过30度需要向用户发送高温预警提示")
                        .build())
                .messages(UserMessage.builder()
                        .text("今天上海热不热？")
                        .build())
                .chatOptions(options)
                .build();

        // 调用AI模型
        ChatResponse chatResponse = chatModel.call(prompt);

        // 是否存在工具调用的请求
        while (chatResponse.hasToolCalls()) {
            // 使用模拟的 ToolCallingManager 去执行工具，并得倒工具执行的结果
            ToolExecutionResult toolExecutionResult = exampleToolCallingManager.executeToolCalls(prompt, chatResponse);

            Prompt toolCallPrompt = Prompt.builder()
                    // 工具执行的结果封装为消息，并包含此次与AI模型交互的整个上下文消息
                    .messages(toolExecutionResult.conversationHistory())
                    .chatOptions(options)
                    .build();

            // 将工具调用的结果附加给AI模型
            chatResponse = chatModel.call(toolCallPrompt);
        }
        log.info("\n[ControllerToolExecution] response content -> \n{}", chatResponse.getResult().getOutput().getText());
    }
}
