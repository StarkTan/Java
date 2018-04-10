package com.stark.ioctest.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by Stark on 2017/12/1.
 */
@Component
public class ScheduledTask {

    @Scheduled(cron = "0 * * * * ? ")
    public void test(){
        System.out.println(new Date());
    }
}
