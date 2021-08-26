package com.example.crmgo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.example.crmgo.register.mapper")
@SpringBootApplication
public class CrmgoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmgoApplication.class, args);
    }
}
