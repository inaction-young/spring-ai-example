package com.spring.ai.example.prompt.two;


import com.spring.ai.example.advisor.three.record.UserInfo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @fileName PromptExampleTest
 * @description:
 * @author: tj
 * @date 2025年07月02日 13:50
 */
@SpringBootTest
public class PromptExampleTest {

    @Resource
    private PromptExample promptExample;

    @Test
    public void promptExample() {
        promptExample.promptExample();
    }

    @Test
    public void promptTemplateExample() {
        promptExample.promptTemplateExample("你认识我吗？", new UserInfo("重案组之虎曹达华"));
    }

    @Test
    public void promptTemplateResourceExample() {
        promptExample.promptTemplateResourceExample("你也听说过我的故事？", new UserInfo("重案组之虎曹达华"));
    }

    @Test
    public void promptTemplateRendererExample() {
        promptExample.promptTemplateRendererExample();
    }

}
