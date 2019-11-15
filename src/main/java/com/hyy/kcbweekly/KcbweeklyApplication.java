package com.hyy.kcbweekly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hyy.kcbweekly.weekly.mapper")
public class KcbweeklyApplication {

    public static void main(String[] args) {
        SpringApplication.run(KcbweeklyApplication.class, args);
    }

}
