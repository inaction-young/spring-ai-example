package com.spring.ai.example.tools.one;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.Map;

/**
 * @fileName MockWeatherTools
 * @description:
 * @author: tj
 * @date 2025年07月10日 10:54
 */
@Slf4j
public class MockWeatherTools {

    private String mockData = "{\"location\":{\"name\":\"%s\",\"path\":\"中国, %s\"},\"now\":{\"precipitation\":0,\"temperature\":31.5,\"pressure\":1005,\"humidity\":43,\"windDirection\":\"西南风\",\"windDirectionDegree\":216,\"windSpeed\":2.7,\"windScale\":\"微风\",\"feelst\":35}}";

    @Tool(description = "获取一个城市的天气，需要输入城市的名称")
    public Object getWeather(@ToolParam(description = "城市。如湖南长沙") String city) {
        log.info("\n获取天气，城市 -> {}", city);
        return String.format(mockData, city, city);
    }

    @Tool(description = "向用户发送高温预警，需要输出城市和气温")
    public Object sendWarning(@ToolParam(description = "城市。如湖南长沙") String city,
                              @ToolParam(description = "气温") double temperature) {
        log.info("\n发送高温预警，城市 -> {}, 气温 -> {}", city, temperature);
        return "发送成功";
    }

    @Tool(description = "获取一个位置的海拔，需要提供一个位置")
    public Object getAltitude(@ToolParam(description = "位置。如长沙岳麓山") String location, ToolContext context) {
        log.info("\n从上下文中获取海拔，位置 -> {}", location);
        Object altitude = context.getContext().get("altitude");
        return Map.of("location", location, "altitude", altitude, "unit" , "千米");
    }

}
