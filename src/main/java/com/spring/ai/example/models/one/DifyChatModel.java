package com.spring.ai.example.models.one;


import com.ai.chat.dify.core.client.DifyApiClient;
import com.ai.chat.dify.core.client.DifyClientContext;
import com.ai.chat.dify.core.request.chat.ChatMessagesRequest;
import com.ai.chat.dify.core.response.chat.ChatCompletionResponse;
import com.spring.ai.example.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.metadata.ChatGenerationMetadata;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.metadata.DefaultUsage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.ChatOptions;
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

    /** 这是作者以前写的一个调用 Dify 的客户端，直接引入用一用 */
    private DifyApiClient difyApiClient;

    /** apiKey */
    private String difyApiKey;

    private final String CONVERSATION_ID = "CONVERSATION_ID";

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

    /**
     * 将Dify返回的元数据转换
     * @param response
     * @return
     */
    private ChatResponseMetadata createMetadata(ChatCompletionResponse response) {
        return ChatResponseMetadata.builder()
                .id(response.getMessageId())
                .usage(new DefaultUsage(
                        response.getMetadata().getUsage().getPromptTokens(),
                        response.getMetadata().getUsage().getCompletionTokens(),
                        response.getMetadata().getUsage().getTotalTokens())
                ).build();
    }

    /**
     * 将Dify返回的消息转换
     * @param response
     * @return
     */
private List<Generation> createGeneration(ChatCompletionResponse response) {
    AssistantMessage assistantMessage = new AssistantMessage(response.getAnswer());
    ChatGenerationMetadata chatGenerationMetadata = ChatGenerationMetadata.builder()
            .metadata(CONVERSATION_ID, response.getConversationId())
            .build();
    return List.of(new Generation(assistantMessage, chatGenerationMetadata));
}

    /**
     * 将提示转换成Dify的请求
     * @param prompt
     * @return
     */
    private DifyClientContext<ChatMessagesRequest> createRequestContext(Prompt prompt) {
        ChatMessagesRequest request = new ChatMessagesRequest();
        request.setQuery(prompt.getContents());
        request.setUser("123456");
        request.setConversationId("");

        ChatOptions options = prompt.getOptions();
        // 使用传递的选项参数
        if (options instanceof DifyChatOptions difyChatOptions) {
            request.setUser(difyChatOptions.getUser());
            request.setConversationId(difyChatOptions.getConversationId());
            return new DifyClientContext<>(StringUtils.isBlank(difyChatOptions.getModel()) ? difyApiKey : difyChatOptions.getModel(), request);
        }

        return new DifyClientContext<>(difyApiKey, request);
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
