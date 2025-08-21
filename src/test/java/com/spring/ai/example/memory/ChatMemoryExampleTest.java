package com.spring.ai.example.memory;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

/**
 * @fileName ChatMemoryExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月16日 15:04
 */
@SpringBootTest
public class ChatMemoryExampleTest {

    @Resource
    private ChatMemoryExample chatMemoryExample;

    @Test
    public void chatMemoryExample() {
        String cid = chatMemoryExample.createChat();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            chatMemoryExample.chatMemoryExample(scanner.nextLine(), cid);
        }
    }

    @Resource
    private JdbcChatMemoryExample jdbcChatMemoryExample;

    @Test
    public void jdbcChatMemoryExample() {
        String cid = jdbcChatMemoryExample.createChat();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            jdbcChatMemoryExample.chatMemoryExample(scanner.nextLine(), cid);
        }
    }
}
