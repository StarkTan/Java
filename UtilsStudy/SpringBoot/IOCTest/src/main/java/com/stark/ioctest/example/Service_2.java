package com.stark.ioctest.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Stark on 2017/11/30.
 */
@Service
public class Service_2 implements IService {

    public Service_2() {
        System.out.println("Service_2被加载");
    }

    @Value("Service2") //获取properties中的值
    private String name;

    @Override
    public String getServiceName() {
        return "Service2";
    }
}
