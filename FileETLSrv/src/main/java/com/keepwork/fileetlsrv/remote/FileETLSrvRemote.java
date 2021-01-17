package com.keepwork.fileetlsrv.remote;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "hello-remote", url = "http://localhost:8081", fallback = FileETLSrvRemoteHystrix.class)
public interface FileETLSrvRemote {

    @RequestMapping(value = "/hello1")
    List printDate(@RequestParam(name = "username", required = false) String username);

}
