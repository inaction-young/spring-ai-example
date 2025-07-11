package com.spring.ai.example.tools;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @fileName TravelTools
 * @description:
 * @author: tj
 * @date 2025年07月09日 18:10
 */
public class TravelTools {

    @Tool(description = "获取高铁班次信息")
    public Object getTrain(@ToolParam(description = "出发城市") String departure,
                           @ToolParam(description = "目的地")String destination,
                           @ToolParam(description = "目的地")String datetime) {
        return List.of(
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 512, "seat", "二等座", "train", "G101", "goodsId", "G10101"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 1024, "seat", "一等座", "train", "G101", "goodsId", "G10102"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 2048, "seat", "商务座", "train", "G101", "goodsId", "G10103"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 512, "seat", "二等座", "train", "G201", "goodsId", "G20101"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 1024, "seat", "一等座", "train", "G201", "goodsId", "G20102"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 2048, "seat", "商务座", "train", "G201", "goodsId", "G20103"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 512, "seat", "二等座", "train", "G301", "goodsId", "G30101"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 1024, "seat", "一等座", "train", "G301", "goodsId", "G30102"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 2048, "seat", "商务座", "train", "G301", "goodsId", "G30103")
        );
    }

    @Tool(description = "获取飞机航班信息")
    public Object getFlight(@ToolParam(description = "出发城市") String departure,
                            @ToolParam(description = "目的地")String destination,
                            @ToolParam(description = "目的地")String datetime) {
        return List.of(
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 512, "seat", "经济舱", "flight", "H101", "goodsId", "H10101"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 1024, "seat", "商务舱", "flight", "H101", "goodsId", "H10102"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 2048, "seat", "头等舱", "flight", "H101", "goodsId", "H10103"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 512, "seat", "经济舱", "flight", "H201", "goodsId", "H20101"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 1024, "seat", "商务舱", "flight", "H201", "goodsId", "H20102"),
                Map.of("departure", departure, "destination", destination, "datetime", datetime, "price", 2048, "seat", "头等舱", "flight", "H201", "goodsId", "H20103")
        );
    }

    @Tool(description = "为用户创建机票或火车票行程订单")
    public Object createOrder(@ToolParam(description = "用户ID（userId）") String userId,
                              @ToolParam(description = "机票或火车票商品ID（goodsId）") String goodsId) {
        return Map.of("payLink", "https://www.baidu.com/" + userId + "/" + goodsId);
    }

    @Tool(description = "获取用户所在地的当前日期和时间")
    public Object getDateTime(@ToolParam(description = "用户所在地") String departure) {
        return LocalDateTime.now().toString();
    }

}
