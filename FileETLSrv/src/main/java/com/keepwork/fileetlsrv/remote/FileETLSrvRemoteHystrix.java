package com.keepwork.fileetlsrv.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileETLSrvRemoteHystrix implements FileETLSrvRemote {

    @Override
    public List printDate(@RequestParam(name = "username", required = false) String username) {
        List l = new ArrayList();
        l.add("Sorry");
        return l;
    }
}
