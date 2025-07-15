package com.spring.ai.example.tools.three;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @fileName ToolExecutionExceptionExample
 * @description:
 * @author: tj
 * @date 2025年07月14日 15:28
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ToolExecutionExceptionExample {

    private final ChatClient toolsClient;

    public void toolExecutionException(RuntimeException e) {
        String content = toolsClient.prompt()
                .system("""
                            你需要根据用户输入的内容为其生成SQL。
                            需要验证SQL是否可以执行。
                            如果Sql无法执行，请修改后重试验证是否可执行。
                            最多只能重试验证2次，2次后还是无法执行也不再继续验证了。。
                        """)
                .user("我需要统计所有年级所有科目分数超过90分的学生有多少。")
                .toolCallbacks(FunctionToolCallback.builder("getTableStructure", () -> {
                            log.info("\n[getTableStructure] 获取DDL");
                            return tables;})
                        .description("可以获取表结构，返回的是 DDL 语句")
                        .inputType(Void.class)
                        .build())
                .toolCallbacks(FunctionToolCallback.builder("verifySql", sql -> {
                            log.info("\n[verifySql] 验证语句 -> \n{}", sql);
                            if (Objects.nonNull(e)) {
                                throw new RuntimeException(e);
                            }
                            return Map.of("result", "success");})
                        .description("验证SQL是否可以执行。result字段返回success表示可执行，返回fail表示不可执行并会给出异常内容。")
                        .inputType(Sql.class)
                        .build())
                .call()
                .content();
        log.info("\ncontent -> {}", content);
    }

    public record Sql(@ToolParam(description = "需要验证的SQL语句") String sql) {

    }

    private final String tables = """
            ## 学生表
            CREATE TABLE students (
                student_id INT PRIMARY KEY AUTO_INCREMENT,
                student_name VARCHAR(50) NOT NULL,
                gender CHAR(1) COMMENT 'M-男, F-女',
                birth_date DATE,
                grade_id INT NOT NULL COMMENT '年级ID',
                class_id INT COMMENT '班级ID',
                admission_date DATE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                INDEX idx_grade (grade_id),
                INDEX idx_class (class_id)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
            
            ## 年级表
            CREATE TABLE grades (
                grade_id INT PRIMARY KEY AUTO_INCREMENT,
                grade_name VARCHAR(20) NOT NULL COMMENT '如：一年级、二年级等',
                grade_level INT NOT NULL COMMENT '年级数字级别，1-12',
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
            
            ## 科目表
            CREATE TABLE subjects (
                subject_id INT PRIMARY KEY AUTO_INCREMENT,
                subject_name VARCHAR(50) NOT NULL,
                subject_code VARCHAR(20) COMMENT '科目编码',
                is_required TINYINT(1) DEFAULT 1 COMMENT '1-必修, 0-选修',
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
            
            ## 成绩表
            CREATE TABLE scores (
                score_id INT PRIMARY KEY AUTO_INCREMENT,
                student_id INT NOT NULL,
                subject_id INT NOT NULL,
                exam_id INT COMMENT '考试ID',
                score DECIMAL(5,2) NOT NULL COMMENT '分数',
                exam_date DATE NOT NULL,
                semester VARCHAR(20) COMMENT '学期，如：2023-2024上学期',
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                UNIQUE KEY uk_student_subject_exam (student_id, subject_id, exam_id),
                INDEX idx_student (student_id),
                INDEX idx_subject (subject_id),
                INDEX idx_exam (exam_id),
                INDEX idx_score (score),
                FOREIGN KEY (student_id) REFERENCES students(student_id),
                FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
            
            
            ## 考试表
            CREATE TABLE exams (
                exam_id INT PRIMARY KEY AUTO_INCREMENT,
                exam_name VARCHAR(100) NOT NULL,
                exam_type VARCHAR(20) COMMENT '期中、期末、模拟考等',
                exam_date DATE,
                grade_id INT COMMENT '关联年级',
                semester VARCHAR(20),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (grade_id) REFERENCES grades(grade_id)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
            """;


}
