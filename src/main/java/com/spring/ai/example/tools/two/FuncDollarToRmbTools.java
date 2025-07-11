package com.spring.ai.example.tools.two;


import com.spring.ai.example.utils.ConvertorUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.ToolParam;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;

/**
 * @fileName FuncRmbExchangeRateTools
 * @description:
 * @author: tj
 * @date 2025年07月10日 14:17
 */
@Slf4j
public class FuncDollarToRmbTools implements Function<FuncDollarToRmbTools.Request, FuncDollarToRmbTools.Response> {

    private final BigDecimal rate = BigDecimal.valueOf(7.17);

    @Override
    public Response apply(Request request) {
        log.info("\n美元转换成人民币，参数 -> {}。", ConvertorUtils.toJsonString(request));

        return new Response(request.dollar()
                .multiply(rate)
                .multiply(request.unit().getUnitMultiple())
                .setScale(2));
    }

    @Getter
    @AllArgsConstructor
    public enum Unit {

        @ToolParam(description = "分")
        F(100),

        @ToolParam(description = "角")
        J(10),

        @ToolParam(description = "元")
        Y(1)

        ;

        private int multiple;

        public BigDecimal getUnitMultiple() {
            return BigDecimal.valueOf(this.getMultiple());
        }

    }

    public record Request(@ToolParam(description = "需要转换成人民币的美元数量。") BigDecimal dollar,
                          @ToolParam(required = false, description = "人民币单位。默认：分") Unit unit) {
        public Request {
            if(Objects.isNull(unit)) {
                unit = Unit.F;
            }
        }

}

public record Response(BigDecimal rmb) {

}

}
