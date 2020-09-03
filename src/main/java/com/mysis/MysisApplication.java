package com.mysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 */
@SpringBootApplication
public class MysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysisApplication.class, args);
        System.out.println("启动成功！");
    }
}
