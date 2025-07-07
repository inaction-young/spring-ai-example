package com.spring.ai.example.multimodality;


import com.spring.ai.example.advisor.three.ContextKeys;
import com.spring.ai.example.advisor.three.LogExampleAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @fileName MultimodalityClientConfiguration
 * @description:
 * @author: tj
 * @date 2025年07月07日 17:31
 */
@Configuration
public class MultimodalityClientConfiguration {

    @Bean
    public ChatClient multimodalityClient(OpenAiChatModel chatModel) {
        return ChatClient.builder(chatModel)
                // 添加 Advisors
                .defaultAdvisors(spec -> {
                    spec.params(Map.of(
                            // 放置客户端名称
                            ContextKeys.ClientName.name(), "multimodalityClient"
                    ));

                    spec.advisors(
                            new LogExampleAdvisor() // Log
                    );
                })
                .build();
    }
}
