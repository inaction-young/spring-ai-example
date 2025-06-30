package com.spring.ai.example.advisor.three;


import com.spring.ai.example.advisor.three.record.UserInfo;
import com.spring.ai.example.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import reactor.core.publisher.Flux;

import java.util.Objects;

/**
 * @fileName PromptEnhancementExampleAdvisor
 * @description:
 * @author: tj
 * @date 2025年06月25日 19:05
 */
@Slf4j
public class PromptEnhancementExampleAdvisor implements CallAdvisor, StreamAdvisor {

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        return callAdvisorChain.nextCall(enhancement(chatClientRequest));
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        return streamAdvisorChain.nextStream(enhancement(chatClientRequest));
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        return AdvisorOrders.PromptEnhancementExample.getOrder();
    }

    /**
     * 增强
     * @param chatClientRequest
     * @return
     */
    private ChatClientRequest enhancement(ChatClientRequest chatClientRequest) {
        Object PromptEnhancement = chatClientRequest.context().get(ContextKeys.PromptEnhancement.name());
        if (!Boolean.parseBoolean(StringUtils.toString(PromptEnhancement))) {
            return chatClientRequest;
        }
        UserInfo userInfo = (UserInfo) chatClientRequest.context().get(ContextKeys.UserInfo.name());

        // 假设根据用户相关信息检测到用户是一个小朋友以及提问的类型是大自然
        String sysMsg = "你是一个专门面向小朋友对大自然科普的老师。\n向你提问的是爱好大自然的小朋友，请耐心为解答大自然的问题。\n" + (Objects.isNull(userInfo) ? StringUtils.EMPTY : String.format("小朋友的名字叫：%s", userInfo.name()));
        String userTemplate = "老师你好！我想要问的大自然问题是：%s";
        // 需要异变一个新的 request 设置新的 prompt
        return chatClientRequest.mutate()
                .prompt(chatClientRequest.prompt()
                        .augmentSystemMessage(systemMessage -> SystemMessage.builder().text(sysMsg).build())
                        .augmentUserMessage(userMessage -> UserMessage.builder().text(String.format(userTemplate, userMessage.getText())).build())
                ).build();

    }
}
