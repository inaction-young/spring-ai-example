package com.spring.ai.example.memory;


import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepositoryDialect;

/**
 * @fileName AIChatMemoryRepositoryDialect
 * @description:
 * @author: tj
 * @date 2025年08月19日 15:25
 */
public class AIChatMemoryRepositoryDialect implements JdbcChatMemoryRepositoryDialect {
    @Override
    public String getSelectMessagesSql() {
        return """
                SELECT 
                  message, message_type
                FROM
                  AI_CHAT_MEMORY
                WHERE
                  conversation_id = ?
                ORDER BY
                  sort
               """;
    }

    @Override
    public String getInsertMessageSql() {
        return """
                INSERT INTO 
                AI_CHAT_MEMORY (conversation_id, message, message_type, sort)
                VALUES (?, ?, ?, ?)
               """;
    }

    @Override
    public String getSelectConversationIdsSql() {
        // 没什么意义，暂不实现
        return "";
    }

    @Override
    public String getDeleteMessagesSql() {
        // 用逻辑删除
        return "UPDATE AI_CHAT_MEMORY SET is_deleted = 1 WHERE conversation_id = ?";
    }
}
