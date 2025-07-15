package com.spring.ai.example.tools.four;


import com.spring.ai.example.tools.four.recode.TravelSchedule;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.List;

/**
 * @fileName TravelTools
 * @description:
 * @author: tj
 * @date 2025年07月09日 18:10
 */
public class TravelToolCalling {

    @Tool(description = "获取飞机/火车出行班次信息", resultConverter = JsonSchemaResultConverter.class)
    public List<TravelSchedule> getTravelSchedule(@ToolParam(description = "出发城市") String departure,
                                                  @ToolParam(description = "目的地")String destination,
                                                  @ToolParam(description = "出发日期(格式：YYYY-MM-DD)")String date,
                                                  @ToolParam(description = "出行方式。") TravelSchedule.TravelMode mode) {
        List<TravelSchedule> trainSchedules = List.of(
                new TravelSchedule(departure, destination, date + " 10:00:00", TravelSchedule.TravelMode.TRAIN, 512, "二等座", "G101", "G10101"),
                new TravelSchedule(departure, destination, date + " 10:00:00", TravelSchedule.TravelMode.TRAIN, 1024, "一等座", "G101", "G10102"),
                new TravelSchedule(departure, destination, date + " 10:00:00", TravelSchedule.TravelMode.TRAIN, 2048, "商务座", "G101", "G10103"),
                new TravelSchedule(departure, destination, date + " 11:00:00", TravelSchedule.TravelMode.TRAIN, 512, "二等座", "G201", "G20101"),
                new TravelSchedule(departure, destination, date + " 11:00:00", TravelSchedule.TravelMode.TRAIN, 1024, "一等座", "G201", "G20102"),
                new TravelSchedule(departure, destination, date + " 13:00:00", TravelSchedule.TravelMode.TRAIN, 2048, "商务座", "G201", "G10103"),
                new TravelSchedule(departure, destination, date + " 13:00:00", TravelSchedule.TravelMode.TRAIN, 512, "二等座", "G201", "G30101"),
                new TravelSchedule(departure, destination, date + " 13:00:00", TravelSchedule.TravelMode.TRAIN, 1024, "一等座", "G201", "G30102"),
                new TravelSchedule(departure, destination, date + " 13:00:00", TravelSchedule.TravelMode.TRAIN, 2048, "商务座", "G201", "G30103")
                );


        List<TravelSchedule> flightSchedules = List.of(
                new TravelSchedule(departure, destination, date + " 17:00:00", TravelSchedule.TravelMode.FLIGHT, 5120, "经济舱", "H101", "H10101"),
                new TravelSchedule(departure, destination, date + " 17:00:00", TravelSchedule.TravelMode.FLIGHT, 10240, "头等舱", "H101", "H10102"),
                new TravelSchedule(departure, destination, date + " 17:00:00", TravelSchedule.TravelMode.FLIGHT, 20480, "商务舱", "H101", "H10103"),
                new TravelSchedule(departure, destination, date + " 19:00:00", TravelSchedule.TravelMode.FLIGHT, 5120, "经济舱", "H201", "H20101"),
                new TravelSchedule(departure, destination, date + " 19:00:00", TravelSchedule.TravelMode.FLIGHT, 10240, "头等舱", "H201", "H20102"),
                new TravelSchedule(departure, destination, date + " 19:00:00", TravelSchedule.TravelMode.FLIGHT, 20480, "商务舱", "H201", "H10103"),
                new TravelSchedule(departure, destination, date + " 23:00:00", TravelSchedule.TravelMode.FLIGHT, 5120, "经济舱", "H301", "H30101"),
                new TravelSchedule(departure, destination, date + " 23:00:00", TravelSchedule.TravelMode.FLIGHT, 10240, "头等舱", "H301", "H30102"),
                new TravelSchedule(departure, destination, date + " 23:00:00", TravelSchedule.TravelMode.FLIGHT, 20480, "商务舱", "H301", "H30103")
                );

        if (mode == TravelSchedule.TravelMode.TRAIN) {
            return trainSchedules;
        }

        if (mode == TravelSchedule.TravelMode.FLIGHT) {
            return flightSchedules;
        }

        flightSchedules.addAll(trainSchedules);
        return flightSchedules;
    }

}
