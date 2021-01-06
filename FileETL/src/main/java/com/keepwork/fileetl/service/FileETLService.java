package com.keepwork.fileetl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileETLService {

    @Autowired
    public ApplicationContext applicationContext;

    public void run(){
        System.out.println("file run");

        //获取bean，调用单文件入库
        FileSingleETLService fileSingleETLService = (FileSingleETLService) applicationContext.getBean("fileSingleETLService");
        fileSingleETLService.run();
    }
}
