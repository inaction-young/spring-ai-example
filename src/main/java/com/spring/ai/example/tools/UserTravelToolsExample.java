package com.spring.ai.example.tools;


import com.spring.ai.example.advisor.three.AdvisorOrders;
import com.spring.ai.example.utils.ConvertorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @fileName UserTravelToolsExample
 * @description:
 * @author: tj
 * @date 2025年07月09日 15:43
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserTravelToolsExample {

    private final ChatMemory chatMemory;

    private final ChatClient toolsClient;

    private String SYSTEM = """
            你是一个定制旅游规划师，请根据用户的需求为用户规划旅游行程计划。
            必须要在用户同意的情况下才可以获取用户的喜好。
            如果用户对你的计划非常感兴趣或者满意，需要询问用户是否需要将计划内容通过短信发送给用户。
            必须要在得到用户的肯定后，才可以将计划内容发送给用户。
            发送成功后询问用户出行方式，如果是公共出行则提示用户可以帮忙预订火车票或机票。
            在确认用户的出发地、出行时间后给出班次信息让用户选择。
            在用户确定航班后直接为用户预订火车票或机票创建订单并返回支付链接。
            """;
    private String USER = """
            用户是否允许获取喜好：%s
            用户需求：
            ```%s```
            用户信息：
            ```%s```
            """;

    public String firstToolsExample(String input, boolean getUserPreference) {
        return toolsClient.prompt()
                .system(SYSTEM)
                .user(String.format(
                        USER,
                        getUserPreference ? "是" : "否",
                        input,
                        ConvertorUtils.toJsonString(Map.of(
                                "userId", "u_123456789",
                                "userName", "旋风小子"))))
                .tools(new UserTools(), new TravelTools())
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory)
                        .order(AdvisorOrders.LogExample.getOrder() - 10)
                        .build())
                .call()
                .content();
    }

    public String continueToolsExample(String input) {
        return toolsClient.prompt()
                .system(SYSTEM)
                .user(input)
                .tools(new UserTools(), new TravelTools())
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory)
                        .order(AdvisorOrders.LogExample.getOrder() - 10)
                        .build())
                .call()
                .content();
    }

}
