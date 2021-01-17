package com.keepwork.fileetl;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.keepwork.fileetl.mapper")
@SpringBootApplication
@EnableScheduling
public class FileETLApp {
    public static void main(String[] args) {
        // Spring应用启动起来
        SpringApplication.run(FileETLApp.class,args);
    }
}
