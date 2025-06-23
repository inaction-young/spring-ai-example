package com.spring.ai.example.client.one;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @fileName ChatClientConfiguration
 * @description:
 * @author: tj
 * @date 2025年06月20日 13:48
 */
@Configuration
public class ChatClientConfiguration {

    private static final String DEFAULT_PROMPT = "你是一个Java专家，请帮忙解答提出的Java相关问题。";

    @Bean
    public ChatClient deepseekClient(DeepSeekChatModel chatModel) {
        // 其实也是调用的构造者模式
        return ChatClient.create(chatModel);
    }

    @Bean
    public ChatClient openAiClient(OpenAiChatModel chatModel) {
        // 构造者模式
        return ChatClient.builder(chatModel)
                .defaultSystem(DEFAULT_PROMPT)
                .build();
    }

}
