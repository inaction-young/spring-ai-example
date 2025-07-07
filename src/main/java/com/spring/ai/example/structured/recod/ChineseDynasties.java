package com.spring.ai.example.structured.recod;


import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("这是输出中国王朝需要遵守的JSON格式")
public record ChineseDynasties(@JsonPropertyDescription("王朝名称") String dynasty,
                               @JsonPropertyDescription("王朝存续时长") int reignDuration,
                               @JsonPropertyDescription("王朝建立时间") String beginAt,
                               @JsonPropertyDescription("王朝灭亡时间") String endAt,
                               @JsonPropertyDescription("王朝一共在位皇帝数量") int emperorCount,
                               @JsonPropertyDescription("王朝的第一位皇帝") String firstEmperor,
                               @JsonPropertyDescription("王朝的最后一位皇帝") String lastEmperor) {

}
