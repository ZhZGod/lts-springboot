package com.example.lts.test.task.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTestScheduling {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void oneMin() {
        System.out.println("【每秒任务】我是spring boot 定时任务,");
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void oneHoure() {
        System.out.println("【每小时任务】我是spring boot 定时任务");
    }
}
