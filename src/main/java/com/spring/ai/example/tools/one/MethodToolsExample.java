package com.spring.ai.example.tools.one;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.ai.tool.metadata.ToolMetadata;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.ai.tool.support.ToolDefinitions;
import org.springframework.ai.util.json.schema.JsonSchemaGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @fileName ToolsExample
 * @description:
 * @author: tj
 * @date 2025年07月10日 10:51
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MethodToolsExample {

    private final ChatClient toolsClient;

    public void toolsExample() {
        String content = toolsClient.prompt()
                .user("上海今天天气如何？")
                // 添加工具对象
                .tools(new MockWeatherTools())
                .call()
                .content();
        log.info("\n[toolsExample] content -> \n{}", content);

        content = toolsClient.prompt()
                // 要求发送高温预警
                .system("气温超过30度须向用户发送高温预警")
                .user("今天北京天气好不好？")
                // 添加工具对象。 其实 .tools() 也是使用的 ToolCallbacks.from()
                .toolCallbacks(ToolCallbacks.from(new MockWeatherTools()))
                .call()
                .content();
        log.info("\n[toolsExample]toolCallbacks content -> \n{}", content);
    }

    public void toolsContextExample() {
        String content = toolsClient.prompt()
                .user("上海海拔高不高嘛？")
                .tools(new MockWeatherTools())
                .toolContext(Map.of("altitude", 18.8))
                .call()
                .content();
        log.info("\n[toolsExample] content -> \n{}", content);
    }

    public void manualToolsExample() {
        // ReflectionUtils 是Spring的工具类
        Method method = ReflectionUtils.findMethod(ManualMockWeatherTools.class, "getWeather", String.class);

        ToolDefinition definition = ToolDefinition.builder()
                .name("getWeather")
                .description("获取天气。")
                // JsonSchemaGenerator 是SpringAI提供的工具类
                .inputSchema(JsonSchemaGenerator.generateForMethodInput(method))
                .build();

//        ToolMetadata metadata = ToolMetadata.builder()
//                .returnDirect(false)
//                .build();

        MethodToolCallback toolCallback = MethodToolCallback.builder()
                .toolDefinition(definition)
                .toolMethod(method)
                //元数据信息，目前只有是否直接返回设置
                //.toolMetadata(metadata)
                // 静态方法可以不传Object
                //.toolObject( new ManualMockWeatherTools())
                // 结果转换器，用默认的。
                //.toolCallResultConverter(new DefaultToolCallResultConverter())
                .build();

        String content = toolsClient.prompt()
                .user("长沙天气如何？")
                .toolCallbacks(toolCallback)
                .call()
                .content();

        log.info("\n[manualToolsExample] toolCallbacks content -> \n{}", content);
    }

    public void simplifyManualToolsExample() {
        Method method = ReflectionUtils.findMethod(ManualMockWeatherTools.class, "getWeather", String.class);

        MethodToolCallback toolCallback = MethodToolCallback.builder()
                // 使用 ToolDefinitions.from
                .toolDefinition(ToolDefinitions.from(method))
                // 使用 ToolMetadata.from
                .toolMetadata(ToolMetadata.from(method))
                .toolMethod(method)
                //.toolObject( new ManualMockWeatherTools())
                //.toolCallResultConverter(new DefaultToolCallResultConverter())
                .build();

        String content = toolsClient.prompt()
                .user("永州天气如何？")
                .toolCallbacks(toolCallback)
                .call()
                .content();
        log.info("\n[simplifyManualToolsExample] toolCallbacks content -> \n{}", content);
    }




}
