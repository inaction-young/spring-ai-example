package com.spring.ai.example.advisor.three;


import com.spring.ai.example.utils.ConvertorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientMessageAggregator;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import reactor.core.publisher.Flux;

/**
 * @fileName LogExampleAdvisor
 * @description:
 * @author: tj
 * @date 2025年06月26日 14:39
 */
@Slf4j
public class LogExampleAdvisor implements CallAdvisor, StreamAdvisor {

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        requestLogger(chatClientRequest);
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        responseLogger(chatClientResponse);
        return chatClientResponse;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        requestLogger(chatClientRequest);
        Flux<ChatClientResponse> chatClientResponseFlux = streamAdvisorChain.nextStream(chatClientRequest);
        // ChatClientMessageAggregator 是SpringAi提供的工具类
        return new ChatClientMessageAggregator().aggregateChatClientResponse(chatClientResponseFlux, this::responseLogger);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        return AdvisorOrders.LogExample.getOrder();
    }

    private void requestLogger(ChatClientRequest chatClientRequest) {
        log.info("\nChat client request to AI\nprompt text -> {}\ncontext -> {}", chatClientRequest.prompt().getContents(), ConvertorUtils.toJsonString(chatClientRequest.context()));
    }

    private void responseLogger(ChatClientResponse chatClientResponse) {
        log.info("\nChat client response from AI\noutput text -> {}\ncontext -> {}", chatClientResponse.chatResponse().getResult().getOutput().getText(), chatClientResponse.context());
    }

}
