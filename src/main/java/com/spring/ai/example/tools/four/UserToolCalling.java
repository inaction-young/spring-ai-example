package com.spring.ai.example.tools.four;


import com.spring.ai.example.tools.four.recode.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @fileName UserTools
 * @description:
 * @author: tj
 * @date 2025年07月09日 15:28
 */
@Slf4j
public class UserToolCalling {

    @Tool(name = "getUserPreference", description = "获取用户的喜好及信息")
    public Object getUserPreference(ToolContext context) {
        UserInfo userInfo = getUserInfo(context);
        log.info("\n获取用户的喜好，用户 -> {}", userInfo.id());
        return  Map.of("name", userInfo.name(), "preference", "美食、美酒、美景、游山玩水、放松、惬意");
    }

    @Tool(name = "sendPlanToUser", description = "向用户发送规划好的旅游行程计划。没有返回默认发送成功。")
    public void sendToUser(@ToolParam(description = "行程计划内容") String content, ToolContext context) {
//        log.info("\n向用户发送规划好的旅游行程计划，用户 -> {}， 内容 -> {}", getUserInfo(context).id(), content);
        log.info("\n向用户发送规划好的旅游行程计划。用户 -> {}", getUserInfo(context).id());
    }

    @Tool(description = "获取用户所在地的当前日期和时间")
    public Object getDateTime(@ToolParam(description = "用户所在地的时区") String departure) {
        log.info("\n获取用户所在地的当前日期和时间，用户所在地的时区 -> {}", departure);
        return LocalDateTime.now().toString();
    }

    @Tool(description = "为用户创建机票或火车票行程订单", returnDirect = true)
    public String createOrder(@ToolParam(description = "购票商品ID（出行班次信息中的`goodsId`字段）") String goodsId,
                              ToolContext context) {
        return "已为您创建订单，去付款请点击链接： https://www.baidu.com/" + getUserInfo(context).id() + "/" + goodsId;
    }

    private UserInfo getUserInfo(ToolContext context) {
        return (UserInfo) context.getContext().get("userInfo");
    }
}
