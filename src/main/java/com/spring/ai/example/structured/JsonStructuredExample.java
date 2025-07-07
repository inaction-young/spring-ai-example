package com.spring.ai.example.structured;


import com.spring.ai.example.structured.recod.ChineseDynasties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @fileName JsonStructuredExample
 * @description:
 * @author: tj
 * @date 2025年07月04日 16:38
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JsonStructuredExample {

private final ChatClient promptClient;

    public void example() {
        ChineseDynasties entity = promptClient.prompt()
                .user("请列出中国古代统治时间最长的一个大一统王朝。")
                .call()
                .entity(new JsonStructuredConverter<>(ChineseDynasties.class));
    }

    public void exampleList() {
        List<ChineseDynasties> entitys = promptClient.prompt()
                .user("请列出中国古代统治的所有大一统王朝。")
                .call()
                .entity(new JsonStructuredConverter<>(new ParameterizedTypeReference<List<ChineseDynasties>>(){}));
    }

}
