package com.spring.ai.example.advisor.one;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @fileName LogAdvisorClientConfiguration
 * @description:
 * @author: tj
 * @date 2025年06月25日 14:31
 */
@Configuration
public class LogAdvisorClientConfiguration {

    @Bean
    public ChatClient logAdvisorClient(DeepSeekChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultAdvisors(spec -> {
                    // 参数传递
                    spec.params(Map.of(
                            "ClientName", "multiAdvisorClient"
                    ));

                    // 添加 advisors
                    spec.advisors(List.of(new LogAdvisor()));
                })
                .build();
    }


}
