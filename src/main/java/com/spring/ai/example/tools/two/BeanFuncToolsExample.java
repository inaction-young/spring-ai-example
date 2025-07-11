package com.spring.ai.example.tools.two;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

/**
 * @fileName BeanFuncToolsExample
 * @description:
 * @author: tj
 * @date 2025年07月10日 14:43
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BeanFuncToolsExample {

    private final ChatClient toolsClient;

    public void beanRmbToolsExample() {
        String content = toolsClient.prompt()
                .user("33美元可以兑换多少元人名币？")
                .toolNames("beanRmbTools")
                .call()
                .content();
        log.info("\n[funcToolsExample] unit（元） content -> \n{}", content);
    }

    public void beanDataToolsExample() {
        String content = toolsClient.prompt()
                .user("下个月5号是星期几？")
                // 直接添加Bean(工具)名称就行
                .toolNames("beanDataTools")
                .call()
                .content();

        log.info("\n[simpleFuncToolsExample] content -> \n{}", content);
    }
}
