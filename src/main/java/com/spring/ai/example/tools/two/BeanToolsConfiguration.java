package com.spring.ai.example.tools.two;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @fileName BeanToolsConfiguration
 * @description:
 * @author: tj
 * @date 2025年07月10日 15:42
 */
@Configuration
public class BeanToolsConfiguration {

    @Bean
    @Description("将美元转换成人名币，需要提供美元数量及人民币单位。")
    public Function<FuncDollarToRmbTools.Request, FuncDollarToRmbTools.Response> beanRmbTools() {
        //
        return request -> new FuncDollarToRmbTools.Response(request.dollar()
                .multiply(BigDecimal.valueOf(7.17))
                .multiply(request.unit().getUnitMultiple())
                .setScale(2));
    }

    @Bean
    @Description("获取当前时间。")
    public Supplier<String> beanDataTools() {
        return () -> LocalDateTime.now().toString();
    }
}
