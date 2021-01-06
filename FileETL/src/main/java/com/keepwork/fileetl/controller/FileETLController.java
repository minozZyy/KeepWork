package com.keepwork.fileetl.controller;

import com.keepwork.fileetl.mapper.FileETLMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Slf4j
@Controller

public class FileETLController {

    @Value("${server.port}")
    public String port;

    @Autowired
    public FileETLMapper fileETLMapper;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){

        return "Hello World!" + port;
    }

    @ResponseBody
    @RequestMapping("/hello1")
    public List hello1(){
        MDC.put("LOG_NAME","zhaozhou");
        log.info("zhouyy------------------------------");
        MDC.remove("LOG_NAME");
        return fileETLMapper.Sel();

    }
}
