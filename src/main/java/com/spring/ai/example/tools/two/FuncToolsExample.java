package com.spring.ai.example.tools.two;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @fileName FuncToolsExample
 * @description:
 * @author: tj
 * @date 2025年07月10日 14:43
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FuncToolsExample {

    private final ChatClient toolsClient;

    public void funcToolsExample() {
        FunctionToolCallback<FuncDollarToRmbTools.Request, FuncDollarToRmbTools.Response> toolCallback = FunctionToolCallback
                // 名称和Function工具对象。
                .builder("FuncDollarToRmbTools", new FuncDollarToRmbTools())
                // 描述
                .description("可以将美元转换成人名币的计算器，需要提供人名币数量，以及需要转换成的单位（默认：分）。")
                // 输入类型
                .inputType(FuncDollarToRmbTools.Request.class)
                .build();
        // 不说明单位
        String content = toolsClient.prompt()
                .user("15美元可以兑换多少人名币？")
                .toolCallbacks(toolCallback)
                .call()
                .content();
        log.info("\n[funcToolsExample] content -> \n{}", content);

        // 指定单位
        content = toolsClient.prompt()
                .user("15美元可以兑换多少角人名币？")
                .toolCallbacks(toolCallback)
                .call()
                .content();
        log.info("\n[funcToolsExample] unit（角） content -> \n{}", content);
    }

    public void simpleFuncToolsExample() {
        String content = toolsClient.prompt()
                .user("下个月5号是星期几？")
                .toolCallbacks(FunctionToolCallback
                        .builder("getDatetime", () -> LocalDateTime.now().toString())
                        .description("可以获取当前时间。")
                        // 使用 Void.class
                        .inputType(Void.class)
                        .build())
                .call()
                .content();

        log.info("\n[simpleFuncToolsExample] content -> \n{}", content);
    }


}
