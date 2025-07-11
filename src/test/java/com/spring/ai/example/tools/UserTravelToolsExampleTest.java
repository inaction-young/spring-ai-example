package com.spring.ai.example.tools;


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
public class UserTravelToolsExampleTest {

    @Resource
    private UserTravelToolsExample userTravelToolsExample;

    @Test
    public void example() throws InterruptedException {
        String firstToolsExampleResult = userTravelToolsExample.firstToolsExample("我准备去杭州游玩3-5天，你有什么建议吗？", true);
        System.out.println(firstToolsExampleResult);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();;
            String continueToolsExampleResult = userTravelToolsExample.continueToolsExample(line);
            System.out.println(continueToolsExampleResult);
        }
    }

}
