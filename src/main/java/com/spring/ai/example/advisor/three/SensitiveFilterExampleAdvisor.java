package com.spring.ai.example.advisor.three;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @fileName SensitiveFilterExampleAdvisor
 * @description:
 * @author: tj
 * @date 2025年06月25日 18:21
 */
@Slf4j
public class SensitiveFilterExampleAdvisor implements CallAdvisor, StreamAdvisor {

    // 模拟的敏感词
    private final List<String> words = Lists.newArrayList( "草泥马");

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        if (filter(chatClientRequest)) {
            // 创建过滤的响应
            return createFilterResponse(chatClientRequest);
        }

        return callAdvisorChain.nextCall(chatClientRequest);
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        if (filter(chatClientRequest)) {
            // 创建过滤的响应
            return Flux.just(createFilterResponse(chatClientRequest));
        }

        return streamAdvisorChain.nextStream(chatClientRequest);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        return AdvisorOrders.SensitiveFilterExample.getOrder();
    }

    /**
     * 进行过滤
     * @param chatClientRequest
     * @return
     */
    private boolean filter(ChatClientRequest chatClientRequest) {
        List<UserMessage> userMessages = chatClientRequest.prompt().getUserMessages();
        String text = userMessages.stream().map(UserMessage::getText).collect(Collectors.joining());
        boolean filter = words.stream().anyMatch(text::contains);
        if (filter) {
            // 往上下文添加参数
            chatClientRequest.context().put(ContextKeys.SensitiveFilter.name(), true);
        }
        return filter;
    }

    /**
     * 创建过滤的响应
     * @param chatClientRequest
     * @return
     */
    private ChatClientResponse createFilterResponse(ChatClientRequest chatClientRequest) {
        return ChatClientResponse.builder()
                .chatResponse(ChatResponse.builder()
                        .generations(List.of(new Generation(new AssistantMessage("用户输入存在敏感词"))))
                        .build())
                .context(Map.copyOf(chatClientRequest.context()))
                .build();
    }
}
