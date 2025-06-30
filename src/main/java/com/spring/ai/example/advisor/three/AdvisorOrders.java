package com.spring.ai.example.advisor.three;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.Ordered;

@Getter
@AllArgsConstructor
public enum AdvisorOrders {

    // 在最后，但必须小于 Ordered.LOWEST_PRECEDENCE
    LogExample(Ordered.LOWEST_PRECEDENCE - 1),

    // 在 log 前面
    SensitiveFilterExample(LogExample.order - 1),

    // 在 敏感词过滤 前面
    PromptEnhancementExample(SensitiveFilterExample.order - 1),

    ;

    private int order;
}
