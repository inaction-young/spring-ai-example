package com.spring.ai.example.prompt;


import com.spring.ai.example.advisor.three.ContextKeys;
import com.spring.ai.example.advisor.three.LogExampleAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @fileName PromptClientConfiguration
 * @description:
 * @author: tj
 * @date 2025年06月30日 16:00
 */
@Configuration
public class PromptClientConfiguration {

    @Bean
    public ChatClient promptClient(DeepSeekChatModel chatModel) {
        return ChatClient.builder(chatModel)
                // 添加 Advisors
                .defaultAdvisors(spec -> {
                    spec.params(Map.of(
                            // 放置客户端名称
                            ContextKeys.ClientName.name(), "promptClient"
                    ));

                    spec.advisors(
                            new LogExampleAdvisor() // Log
                    );
                })
                .build();
    }


}
