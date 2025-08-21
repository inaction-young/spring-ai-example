package com.spring.ai.example.memory;


import com.spring.ai.example.advisor.three.LogExampleAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @fileName ChatMemoryClientConfiguration
 * @description:
 * @author: tj
 * @date 2025年07月16日 14:28
 */
@Configuration
public class ChatMemoryClientConfiguration {

    @Bean
    public ChatClient chatMemoryClient(DeepSeekChatModel chatModel) {
        // 聊天记忆
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
                // 最大5条聊天记录
                .maxMessages(5)
                .build();

        // 自动从 chatMemory 增加&检索记忆的 Advisor
        MessageChatMemoryAdvisor chatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory)
                // 优先级高一些
                .order(Ordered.HIGHEST_PRECEDENCE + 10000)
                .build();

        return ChatClient.builder(chatModel)
                // 添加Log Advisor用来输出提示词
                .defaultAdvisors(new LogExampleAdvisor(), chatMemoryAdvisor)
                .build();
    }


    @Bean
    public JdbcChatMemoryRepository jdbcChatMemoryRepository(JdbcTemplate jdbcTemplate) {
        return JdbcChatMemoryRepository.builder()
                .jdbcTemplate(jdbcTemplate)
                // 使用自定义的方言
                .dialect(new AIChatMemoryRepositoryDialect())
                .build();
    }

    @Bean
    public ChatClient jdbcChatMemoryClient(DeepSeekChatModel chatModel,
                                           JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        // 聊天记忆
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
                // 最大5条聊天记录
                .maxMessages(5)
                // 使用JDBC存储
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .build();

        // 自动从 chatMemory 增加&检索记忆的 Advisor
        MessageChatMemoryAdvisor chatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory)
                // 优先级高一些
                .order(Ordered.HIGHEST_PRECEDENCE + 10000)
                .build();

        return ChatClient.builder(chatModel)
                // 添加Log Advisor用来输出提示词
                .defaultAdvisors(new LogExampleAdvisor(), chatMemoryAdvisor)
                .build();
    }

}
