package com.stark.aoptest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Stark on 2018/1/16.
 */
@Controller
@RequestMapping("/aop")
public class AopController {
    @Autowired
    IUserService service;

    @RequestMapping("/add")
    public void testAdd(String id) {
        String name = service.getClass().getName();
        System.out.println(name);
        service.add(id);
    }

    @RequestMapping("/del")
    public void testdel(String id) {
        service.del(id);
    }

//    @RequestMapping("/update")
//    public void testUpdate(String id) {
//        service.update(id);
//    }

}
