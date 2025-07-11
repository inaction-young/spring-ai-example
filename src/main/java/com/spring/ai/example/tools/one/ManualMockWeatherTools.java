package com.spring.ai.example.tools.one;


import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.extern.slf4j.Slf4j;

/**
 * @fileName ManualMockWeatherTools
 * @description:
 * @author: tj
 * @date 2025年07月10日 11:21
 */
@Slf4j
public class ManualMockWeatherTools {

    private static String mockResultStr = "{\"location\":{\"name\":\"%s\",\"path\":\"中国, %s\"},\"now\":{\"precipitation\":0,\"temperature\":31.5,\"pressure\":1005,\"humidity\":43,\"windDirection\":\"西南风\",\"windDirectionDegree\":216,\"windSpeed\":2.7,\"windScale\":\"微风\",\"feelst\":23.1}}";

    protected static Object getWeather(@JsonPropertyDescription("城市") String city) {
        log.info("\n[Manual] 获取天气，城市 -> {}", city);
        return String.format(mockResultStr, city, city);
    }
}
