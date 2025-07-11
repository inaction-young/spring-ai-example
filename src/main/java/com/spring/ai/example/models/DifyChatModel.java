package com.spring.ai.example.models;


import com.ai.chat.dify.core.client.DifyApiClient;
import com.ai.chat.dify.core.client.DifyClientContext;
import com.ai.chat.dify.core.request.chat.ChatMessagesRequest;
import com.ai.chat.dify.core.response.chat.ChatCompletionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.metadata.ChatGenerationMetadata;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.metadata.DefaultUsage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @fileName DifyChatModel
 * @description:
 * @author: tj
 * @date 2025年07月08日 15:11
 */
@Slf4j
public class DifyChatModel implements ChatModel {

    /** 这是以前作者写的一个调用 Dify 的客户端，直接引入用一用 */
    private DifyApiClient difyApiClient;

    private String difyApiKey;

    private String conversationId = "";

    public DifyChatModel(Builder builder) {
        this.difyApiClient = builder.difyApiClient;
        this.difyApiKey = builder.difyApiKey;
    }

    @Override
    public ChatResponse call(Prompt prompt) {
        DifyClientContext<ChatMessagesRequest> requestContext = createRequestContext(prompt);
        ChatCompletionResponse sync = difyApiClient.onChatMessagesRequest(requestContext).sync();
        return ChatResponse.builder()
                .metadata(createMetadata(sync))
                .generations(createGeneration(sync))
                .build();
    }


    @Override
    public Flux<ChatResponse> stream(Prompt prompt) {
        return ChatModel.super.stream(prompt);
    }

    private DifyClientContext<ChatMessagesRequest> createRequestContext(Prompt prompt) {
        ChatMessagesRequest request = new ChatMessagesRequest();
        request.setQuery(prompt.getContents());
        request.setUser("123456");
        request.setConversationId(conversationId);

        return new DifyClientContext<>(difyApiKey, request);
    }

    private ChatResponseMetadata createMetadata(ChatCompletionResponse response) {
        conversationId = response.getConversationId();
        return ChatResponseMetadata.builder()
                .id(response.getMessageId())
                .usage(new DefaultUsage(
                        response.getMetadata().getUsage().getPromptTokens(),
                        response.getMetadata().getUsage().getCompletionTokens(),
                        response.getMetadata().getUsage().getTotalTokens())
                ).build();
    }

    private List<Generation> createGeneration(ChatCompletionResponse response) {
        AssistantMessage assistantMessage = new AssistantMessage(response.getAnswer());
        return List.of(new Generation(assistantMessage, ChatGenerationMetadata.builder().build()));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private DifyApiClient difyApiClient;

        private String difyApiKey;

        private Builder() {
        }

        public Builder difyApiClient(DifyApiClient difyApiClient) {
            this.difyApiClient = difyApiClient;
            return this;
        }

        public Builder difyApiKey(String difyApiKey) {
            this.difyApiKey = difyApiKey;
            return this;
        }

        public DifyChatModel build() {
            return new DifyChatModel(this);
        }
    }
}
