package com.spring.ai.example.tools.four.recode;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("出行班次信息")
public record TravelSchedule(@JsonPropertyDescription("出发城市") String departure,
                             @JsonPropertyDescription("目的地") String destination,
                             @JsonPropertyDescription("出发时间") String datetime,
                             @JsonPropertyDescription("出行方式") TravelMode mode,
                             @JsonPropertyDescription("票价（单位：分）") int price,
                             @JsonPropertyDescription("座位（舱位）") String seat,
                             @JsonPropertyDescription("班次（航班）") String schedule,
                             @JsonPropertyDescription("购票商品ID（用户预订机票/火车票时需要）") String goodsId) {


    public enum TravelMode {

        @JsonPropertyDescription("不确定的。未知的。未明确说明的。") UNKNOWN,

        @JsonPropertyDescription("火车") TRAIN,

        @JsonPropertyDescription("飞机") FLIGHT,

        ;
    }

}
