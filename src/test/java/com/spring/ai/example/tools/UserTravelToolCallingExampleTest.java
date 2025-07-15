package com.spring.ai.example.tools;


import com.spring.ai.example.tools.four.UserTravelToolCallingExample;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

/**
 * @fileName UserTravelToolsExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月09日 15:54
 */
@SpringBootTest
public class UserTravelToolCallingExampleTest {

    @Resource
    private UserTravelToolCallingExample userTravelToolCallingExample;

    @Test
    public void example() throws InterruptedException {
        System.out.println("系统提示：输入你的需求！");
        Scanner scanner = new Scanner(System.in);
        String firstToolsExampleResult = userTravelToolCallingExample.firstToolsExample(scanner.nextLine(), true);
        System.out.println(firstToolsExampleResult);


        while (true) {
            String line = scanner.nextLine();;
            String continueToolsExampleResult = userTravelToolCallingExample.continueToolsExample(line);
            System.out.println(continueToolsExampleResult);
        }
    }

}
