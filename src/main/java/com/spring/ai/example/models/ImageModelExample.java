package com.spring.ai.example.models;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Component;

/**
 * @fileName ImageModelExample
 * @description:
 * @author: tj
 * @date 2025年07月09日 14:38
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ImageModelExample {

    private final OpenAiImageModel imageModel;

    public void example() {
        ImageResponse imageResponse = imageModel.call(new ImagePrompt(
                "一台蓝色的奥迪S5",
                OpenAiImageOptions.builder()
                        .model("gpt-image-1")
                        .user("test")
                        .quality("auto")
                        .height(256)
                        .height(1024)
                        .build()));
        log.info("\nImage model response -> \n{}", imageResponse);
    }



}
