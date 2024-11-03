package com.easychat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 项目启动类
 * @author Siyuan
 * @date 2024/11/03/21:50
 */
@SpringBootApplication(scanBasePackages = {"com.easychat"}, exclude = DataSourceAutoConfiguration.class)
public class EasyChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyChatApplication.class, args);
    }
}
