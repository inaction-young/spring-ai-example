package com.spring.ai.example.advisor.three;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @fileName MultiAdvisorClientConfiguration
 * @description:
 * @author: tj
 * @date 2025年06月26日 14:55
 */
@Configuration
public class MultiAdvisorClientConfiguration {

    private static final String SYSTEM_PROMPT = "你是一个Java专家，请帮忙解答提出的Java相关问题。";

    @Bean
    public ChatClient multiAdvisorClient(DeepSeekChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem(SYSTEM_PROMPT) // 默认系统提示词时Java专家
                .defaultAdvisors(spec -> {
                    spec.params(Map.of(
                            // 放置客户端名称
                            ContextKeys.ClientName.name(), "multiAdvisorClient",
                            // 敏感词过滤默认是false
                            ContextKeys.SensitiveFilter.name(), false
                    ));

                    spec.advisors(
                            new LogExampleAdvisor(), // Log
                            new PromptEnhancementExampleAdvisor(), // 提示词增强
                            new SensitiveFilterExampleAdvisor() // 敏感词过滤
                    );
                })
                .build();
    }
}
