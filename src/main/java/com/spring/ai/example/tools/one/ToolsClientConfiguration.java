package com.spring.ai.example.tools.one;


import com.spring.ai.example.advisor.three.ContextKeys;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @fileName ToolsClientConfiguration
 * @description:
 * @author: tj
 * @date 2025年07月09日 18:31
 */
@Configuration
public class ToolsClientConfiguration {

    @Bean
    public ChatClient toolsClient(DeepSeekChatModel chatModel) {
        return ChatClient.builder(chatModel)
                // 添加 Advisors
                .defaultAdvisors(spec -> {
                    spec.params(Map.of(
                            // 放置客户端名称
                            ContextKeys.ClientName.name(), "toolsClient"
                    ));
                })
                .build();
    }
}
