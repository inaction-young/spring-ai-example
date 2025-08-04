package com.spring.ai.example.models.one;


import lombok.Getter;
import org.springframework.ai.chat.prompt.ChatOptions;

import java.util.List;

/**
 * @fileName DifyChatOptions
 * @description:
 * @author: tj
 * @date 2025年08月04日 16:35
 */
public class DifyChatOptions implements ChatOptions {

    // 模拟将 difyApiKey 视为Model，在Dify中一个key对应一个应用。
    private String difyApiKey;

    /** 回话ID **/
    @Getter
    private String conversationId;

    /** 用户唯一标识 **/
    @Getter
    private String user;

    @Override
    public String getModel() {
        // 模拟将 difyApiKey 视为 Model
        return difyApiKey;
    }

    /** 下面这些暂不实现 **/
    @Override
    public Double getFrequencyPenalty() {
        return 0.0;
    }

    @Override
    public Integer getMaxTokens() {
        return 0;
    }

    @Override
    public Double getPresencePenalty() {
        return 0.0;
    }

    @Override
    public List<String> getStopSequences() {
        return List.of();
    }

    @Override
    public Double getTemperature() {
        return 0.0;
    }

    @Override
    public Integer getTopK() {
        return 0;
    }

    @Override
    public Double getTopP() {
        return 0.0;
    }

    @Override
    public <T extends ChatOptions> T copy() {
        return null;
    }

    public static DifyChatOptions.Builder builder() {
        return new DifyChatOptions.Builder();
    }


    public static class Builder {

        protected DifyChatOptions options;

        public Builder() {
            this.options = new DifyChatOptions();
        }

        public Builder(DifyChatOptions options) {
            this.options = options;
        }

        public DifyChatOptions.Builder difyApiKey(String difyApiKey) {
            this.options.difyApiKey = difyApiKey;
            return this;
        }

        public DifyChatOptions.Builder conversationId(String conversationId) {
            this.options.conversationId = conversationId;
            return this;
        }

        public DifyChatOptions.Builder user(String user) {
            this.options.user = user;
            return this;
        }

        public DifyChatOptions build() {
            return this.options;
        }
    }
}
