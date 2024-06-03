package com.ccb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ccb.mapper")
public class ProtoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProtoApplication.class, args);
    }
}
