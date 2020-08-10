package com.laughing.laughingsso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.laughing.laughingsso.mapper")
public class LaughingSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaughingSsoApplication.class, args);
    }

}
