package com.spring.ai.example.prompt.one;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

/**
 * @fileName MessageClientExample
 * @description:
 * @author: tj
 * @date 2025年06月30日 17:00
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageClientExample {

    private final ChatClient promptClient;

    public void assistantExample() {
        log.info("\n=======================================第一次交互=======================================");
        Prompt prompt = Prompt.builder()
                .messages(
                        // 用英文作为系统提示词
                        SystemMessage.builder().text("You are a Java expert").build(),
                        // 用户输入了Deque类，默认也是英文
                        UserMessage.builder().text("java.util.Deque").build()
                ).build();

        String content = promptClient.prompt(prompt).call().content();

        log.info("\n=======================================第二次交互=======================================");
        Prompt assistantPrompt = Prompt.builder()
                .messages(
                        // 用户再次要求使用中文输出
                        UserMessage.builder().text("中文").build(),
                        // 并将上一次的输出内容添加在assistant消息作为上下文
                        new AssistantMessage(content)
                ).build();
        String assistantContent = promptClient.prompt(assistantPrompt).call().content();

    }

    public void toolExample() {
        String content = promptClient.prompt()
                // 这里只是做了一层封装，与 SystemMessage.builder() 是一样的，底层还是用的 SystemMessage
                .system("你是一个万能的小助手")
                // 这里也是封装了，底层是 UserMessage
                .user("帮我查询张家界今天天气如何？")
                // 添加工具调用
                .tools(new MockGetWeather())
                .call()
                .content();
    }

    public class MockGetWeather {
        @Tool(name = "mockGetWeather", description = "获取一个位置的天气，但需要先提供一个位置")
        public String mockGetWeather(@ToolParam(description = "城市，如长沙市或湖南省长沙市") String location) {
            //
            return "{\"location\":{\"name\":\"张家界\",\"path\":\"中国, 湖南, 张家界\"},\"now\":{\"precipitation\":0,\"temperature\":31.5,\"pressure\":1005,\"humidity\":43,\"windDirection\":\"西南风\",\"windDirectionDegree\":216,\"windSpeed\":2.7,\"windScale\":\"微风\",\"feelst\":23.1}}";
        }
    }


}
