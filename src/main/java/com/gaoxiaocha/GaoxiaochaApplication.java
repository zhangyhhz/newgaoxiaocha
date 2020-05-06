package com.gaoxiaocha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gaoxiaocha.mapper")
public class GaoxiaochaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaoxiaochaApplication.class, args);
    }

}
