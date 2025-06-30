package com.spring.ai.example.advisor.three;


import com.spring.ai.example.advisor.three.record.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @fileName MultiAdvisorClientExample
 * @description:
 * @author: tj
 * @date 2025年06月26日 14:58
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MultiAdvisorClientExample {

    public final ChatClient multiAdvisorClient;

    /**
     * 一个正常调用看看LogExampleAdvisor是否正常输出
     */
    public void normalExecutionExample() {
        log.info("\nNormal execution!");
        ChatClientResponse chatClientResponse = multiAdvisorClient.prompt()
                .user("在AI热火朝天的现在，Java的优势是否还存在？")
                .call()
                .chatClientResponse();
    }

    /**
     * 提示词增强，看看默认的Java提示词是否被修改
     */
    public void promptEnhancementExample() {
        log.info("\nPrompt enhancement execution!");
        ChatClientResponse chatClientResponse = multiAdvisorClient.prompt()
                // 往上下文里传递开启提示词增强
                .advisors(spce -> spce.params(Map.of(
                        ContextKeys.PromptEnhancement.name(), true,
                        ContextKeys.UserInfo.name(), new UserInfo("大聪明")
                )))
                .user("蜗牛是怎么繁殖的呢？")
                .call()
                .chatClientResponse();
    }

    /**
     * 敏感词过滤，看看是否会直接返回、阻断调用
     */
    public void sensitiveFilterExample() {
        log.info("\nSensitive Filter execution!");
        ChatClientResponse chatClientResponse = multiAdvisorClient.prompt()
                // 往上下文里传递开启提示词增强
                .advisors(spce -> spce.params(Map.of(
                        ContextKeys.PromptEnhancement.name(), true,
                        ContextKeys.UserInfo.name(), new UserInfo("好奇宝子")
                )))
                // 草泥马是敏感词
                .user("草泥马是什么动物啊？")
                .call()
                .chatClientResponse();

        log.info("\nSensitive Filter result text -> \n{}", chatClientResponse.chatResponse().getResult().getOutput().getText());
        log.info("\nSensitive Filter result context -> \n{}", chatClientResponse.context());
    }

}
