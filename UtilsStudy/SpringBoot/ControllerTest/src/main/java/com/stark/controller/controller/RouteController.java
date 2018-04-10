package com.stark.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stark on 2017/11/29.
 * 测试转发和重定向的Controller
 */
@Controller
@RequestMapping("/route")
public class RouteController {
    @RequestMapping("/testredirect")
    private String ReRoute1(String id) {

        //重定向数据不会再传递
        return "redirect:redirect?id=" + id;
    }

    @RequestMapping(value = "/redirect")
    @ResponseBody
    private Object Redirect(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        return map;
    }

    @RequestMapping("/testforword")
    private String ReRoute2(String id) {
        //转发会传递数据
        return "forword";
    }

    @RequestMapping(value = "/forword")
    @ResponseBody
    private Object forword(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        return map;
    }
}
