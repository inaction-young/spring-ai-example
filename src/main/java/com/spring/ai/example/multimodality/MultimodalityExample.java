package com.spring.ai.example.multimodality;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

/**
 * @fileName MultimodalityExample
 * @description:
 * @author: tj
 * @date 2025年07月07日 17:32
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MultimodalityExample {

    private final ChatClient multimodalityClient;

    @Value("classpath:/img/lls.png")
    private Resource imgResource;

    public void example() {
        String user = """
                    1. 请解释图中有什么内容？
                    2. 请围绕图中的内容帮我写一篇100字左右的文章，要有情感共鸣。
                """;

        String content = multimodalityClient.prompt()
                .user(userSpec ->
                        userSpec.text(user)
                                .media(MimeTypeUtils.IMAGE_PNG, imgResource))
                .call()
                .content();
    }
}
