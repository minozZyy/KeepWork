package com.keepwork.fileetl.service;

import com.keepwork.fileetl.mapper.FileETLMapper;
import com.keepwork.fileetl.model.FileETL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileSingleETLService {

    @Autowired
    public FileETLMapper fileETLMapper;

    public void run(){
        System.out.println("single run");
        List<FileETL> fileETLList = fileETLMapper.Sel();
        fileETLList.forEach(it -> {
            System.out.println(it.getUseoffset());
        });
    }
}
