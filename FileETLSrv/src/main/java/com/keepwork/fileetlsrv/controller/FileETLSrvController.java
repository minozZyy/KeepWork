package com.keepwork.fileetlsrv.controller;


import com.keepwork.fileetlsrv.remote.FileETLSrvRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FileETLSrvController {

    @Autowired
    FileETLSrvRemote fileETLSrvRemote;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!" + "8082";
    }

    @RequestMapping("/hello1")
    public List callBackend(@RequestParam(name = "username", required = false) String username) {
        return fileETLSrvRemote.printDate(username);
    }
}
