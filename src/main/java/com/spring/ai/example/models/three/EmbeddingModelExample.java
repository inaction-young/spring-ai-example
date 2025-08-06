package com.spring.ai.example.models.three;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @fileName EmbeddingModelExample
 * @description:
 * @author: tj
 * @date 2025年07月09日 14:54
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmbeddingModelExample {

    private final OpenAiEmbeddingModel embeddingModel;

    public void example() {
        EmbeddingResponse embeddingResponse = embeddingModel.call(
                new EmbeddingRequest(List.of("AI应用开发者"),
                        OpenAiEmbeddingOptions.builder()
                                .model("text-embedding-ada-002")
                                .user("test-1")
                                .build()));
        log.info("\nEmbedding model response -> \n{}", embeddingResponse);
    }
}
