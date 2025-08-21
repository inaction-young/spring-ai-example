CREATE TABLE `AI_CHAT_MEMORY` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `conversation_id` bigint(20) NOT NULL COMMENT '会话ID',
    `message` TEXT NOT NULL COMMENT '消息内容',
    `message_type` ENUM('USER', 'ASSISTANT', 'SYSTEM', 'TOOL') NOT NULL COMMENT '消息类型',
    `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除，0否1是',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_conversation_id` (`conversation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 COMMENT='AI聊天记忆表';



