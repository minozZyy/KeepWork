package com.keepwork.fileetlsrv;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FileETLSrvApp {
    public static void main(String[] args) {
        // Spring应用启动起来
        SpringApplication.run(FileETLSrvApp.class,args);
    }
}
