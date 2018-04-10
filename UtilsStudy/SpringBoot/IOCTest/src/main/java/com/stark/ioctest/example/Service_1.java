package com.stark.ioctest.example;

import org.springframework.stereotype.Service;

/**
 * Created by Stark on 2017/11/30.
 */
@Service //注入到IOC中
public class Service_1 implements IService {
    public Service_1() {
        System.out.println("Service_1被加载");
    }

    @Override
    public String getServiceName() {
        return "service_1";
    }
}
