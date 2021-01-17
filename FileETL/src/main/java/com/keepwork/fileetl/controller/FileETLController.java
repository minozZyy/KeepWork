package com.keepwork.fileetl.controller;

import com.keepwork.fileetl.mapper.FileETLMapper;
import com.keepwork.fileetl.service.HBaseService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    public List hello1(@RequestParam(name = "username", required = false) String username){
        MDC.put("LOG_NAME","zhaozhou");
        //log.info("zhouyy------------------------------");
        MDC.remove("LOG_NAME");
        return fileETLMapper.Sel();

    }

    @ResponseBody
    @RequestMapping("/hbaseCra")
    public void hbaseCra(){
        MDC.put("LOG_NAME","zhaozhou");
        //log.info("zhouyy------------------------------");
        List cfList = new ArrayList();
        cfList.add("info");
        cfList.add("base");

        HBaseService hBaseService = new HBaseService();
        //hBaseService.creatTable("zhouyytest",cfList);
        List allTableList = hBaseService.getAllTableNames();
        for (int i =0;i<allTableList.size();i++) {
            System.out.println(allTableList.get(i));
        }
        MDC.remove("LOG_NAME");
    }
}
