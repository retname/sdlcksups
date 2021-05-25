package com.ordermanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableScheduling
@MapperScan({"com.ordermanager.project.*.*.mapper","com.ordermanager.project.*.mapper"})
public class OrderManagerApplication
{
    public static void main(String[] args)
    {
        
        SpringApplication.run(OrderManagerApplication.class, args);
    }
}