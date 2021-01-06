package com.keepwork.fileetl.scheduled;

import com.keepwork.fileetl.service.FileETLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FileETLScheduled {

    @Value("${Scheduled.monthRate}")
    private String monthRate;

    @Autowired
    private FileETLService fileService;
    //fixedDelay：指定两次任务执行的时间间隔(毫秒)，此时间间隔指的是，前一次任务结束与下一个任务开始的间隔
    //如：@Scheduled(fixedDelay = 5*1000 )，表示第一个任务结束后，过5秒后，开始第二个任务
    //@Scheduled(fixedDelay = 5000)

    //fixedRate：指定两次任务执行的时间间隔(毫秒)，此时间间隔指的是，前一个任务开始与下一个任务开始的间隔
    //如：@Scheduled(fixedRate= 5*1000 )，表示第一个任务开始后(第一个任务执行时间小于5秒)，第一个任务开始后的第6秒，开始第二个任务。如果第一个任务执行时间大于5秒，第一个任务结束后，直接开始第二个任务
    //@Scheduled(fixedRate = 5000)

    //cron：使用表达是进行任务的执行，例如：@Scheduled(cron = "0/15 * * * * ? ")每隔15秒执行一次
    @Scheduled(cron = "${Scheduled.monthRate}")
    public void reportCurrentTime() {
        System.out.println("11>" + monthRate);
        fileService.run();
    }
}
