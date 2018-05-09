package com.stark.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Stark on 2018/5/7.
 * web接口
 */
@RestController
public class HelloController {

    @RequestMapping("/test")
    public Object test() {
        return "11";
    }








}
