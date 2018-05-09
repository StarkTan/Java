package com.stark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Stark on 2018/5/7.
 * web接口
 */
@Controller
public class HelloController {
    @RequestMapping("/test")
    @ResponseBody
    public Object test() {
        return "12";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "world");
        return "hello";
    }

}
