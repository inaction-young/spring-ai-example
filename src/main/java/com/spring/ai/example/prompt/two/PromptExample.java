package com.spring.ai.example.prompt.two;


import com.spring.ai.example.advisor.three.record.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @fileName PromptExample
 * @description:
 * @author: tj
 * @date 2025年07月02日 13:35
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PromptExample {

    private final ChatClient promptClient;

    public void promptExample() {
        // 构造者模式创建Prompt
        Prompt prompt = Prompt.builder()
                .messages(
                        // 用英文作为系统提示词
                        SystemMessage.builder().text("你是一位Java专家。").build(),
                        // 用户输入了Deque类，默认也是英文
                        UserMessage.builder().text("SpringAI有哪些优势").build()
                ).build();

        log.info("\nprompt text -> {}", prompt.getContents());

        // 增强/修改 SystemMessage
        Prompt augmentSystemPrompt = prompt.augmentSystemMessage(systemMessage -> {
            return SystemMessage.builder()
                    .text(systemMessage.getText() + "回答问题要精简，不需要做过多介绍跟解释。")
                    .build();
        });

        log.info("\naugmentSystemPrompt text -> {}", augmentSystemPrompt.getContents());

        // 增强/修改 UserMessage
        Prompt augmentUserPrompt = augmentSystemPrompt.augmentUserMessage(userMessage ->
                UserMessage.builder()
                        .text("帮我解释一下：" + userMessage.getText())
                        .build()
        );

        log.info("\naugmentUserPrompt text -> {}", augmentUserPrompt.getContents());

        // 获取所有的 Message
        SystemMessage systemMessage = augmentUserPrompt.getSystemMessage();
        List<UserMessage> userMessages = augmentUserPrompt.getUserMessages();
        List<Message> instructions = augmentUserPrompt.getInstructions();
        log.info("\nsystemMessage -> {}\nuserMessages -> {}\ninstructions -> {}", systemMessage, userMessages, instructions);
    }

public void promptTemplateExample(String userMsg, UserInfo userInfo) {
    // 通过模版来创建 UserMessage
    Message user = PromptTemplate.builder()
            .template("亲爱的小助手，{msg}")
            .variables(Map.of("msg", userMsg))
            .build()
            .createMessage();

    log.info("\nPromptTemplate createMessage -> {}", user);

    String systemTemplate = "你是一位万能小助手。\n回答用户问题时必须要以“尊敬的用户：XXX，你好！”开头。\n用户信息：{info}";
    // 通过模版来创建 SystemMessage，子类只能通过new
    Message system = new SystemPromptTemplate(systemTemplate)
            .createMessage(Map.of("info", userInfo));

    log.info("\nSystemPromptTemplate createMessage -> {}", system);

    promptClient.prompt()
            .messages(system, user)
            .call()
            .content();
}


    @Value("classpath:/prompts/promptTemplateResourceExample.st")
    private Resource promptTemplateResource;

    public void promptTemplateResourceExample(String userMsg, UserInfo userInfo) {

        Prompt prompt = new SystemPromptTemplate(promptTemplateResource)
                .create(Map.of("info", userInfo));
        log.info("\nSystemPromptTemplate resource createMessage -> {}", prompt.getSystemMessage());

        Message user = PromptTemplate.builder()
                .template("亲爱的小助手，{msg}")
                .variables(Map.of("msg", userMsg))
                .build()
                .createMessage();

        promptClient.prompt(prompt)
                .messages(user)
                .call()
                .content();
    }

    public void promptTemplateRendererExample() {
        Message user = PromptTemplate.builder()
                // 自定义模版占位符规则
                .renderer(StTemplateRenderer.builder()
                        .startDelimiterToken('<')
                        .endDelimiterToken('>')
                        .build())
                .template("亲爱的小助手，<msg>")
                .variables(Map.of("msg", "你在教我做事啊？"))
                .build()
                .createMessage();
        log.info("\nPromptTemplateRenderer createMessage -> {}", user);
    }
}
