package com.spring.ai.example.tools;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.Map;

/**
 * @fileName UserTools
 * @description:
 * @author: tj
 * @date 2025年07月09日 15:28
 */
@Slf4j
public class UserTools {

    @Tool(name = "getUserPreference", description = "获取用户的喜好")
    public Object getUserPreference(@ToolParam(description = "用户ID") String userId) {
        log.info("\n获取用户的喜好，用户 -> {}", userId);
        return  Map.of("preference", "美食、美酒、美景、游山玩水、放松、惬意");
    }

    @Tool(name = "getUserHobby", description = "向用户发送规划好的旅游行程计划")
    public String sendToUser(@ToolParam(description = "用户ID") String userId,
                             @ToolParam(description = "行程计划内容") String content) {
        log.info("\n向用户发送规划好的旅游行程计划，用户 -> {}， 内容 -> {}", userId, content);
        return "发送成功";
    }
}
