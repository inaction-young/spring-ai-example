package com.spring.ai.example.models;


import com.ai.chat.dify.core.client.DifyApiClient;
import com.spring.ai.example.advisor.three.ContextKeys;
import com.spring.ai.example.advisor.three.LogExampleAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @fileName DifyChatClientConfiguration
 * @description:
 * @author: tj
 * @date 2025年07月08日 17:37
 */
@Configuration
public class DifyChatClientConfiguration {

    @Value("${dify.api.key}")
    private String difyApiKey;

    @Bean
    public DifyChatModel difyChatModel(DifyApiClient difyApiClient) {
        return DifyChatModel.builder()
                .difyApiKey(difyApiKey)
                .difyApiClient(difyApiClient)
                .build();
    }

    @Bean
    public ChatClient difyChatClient(DifyChatModel difyChatModel) {
        return ChatClient.builder(difyChatModel)
                // 添加 Advisors
                .defaultAdvisors(spec -> {
                    spec.params(Map.of(
                            // 放置客户端名称
                            ContextKeys.ClientName.name(), "difyChatClient"
                    ));

                    spec.advisors(
                            new LogExampleAdvisor() // Log
                    );
                })
                .build();
    }

}
