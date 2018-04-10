package com.stark.controller.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Strak on 2017/5/13.
 * 测试Controller测试工具
 */
@RestController
public class SimpleController
{
    @RequestMapping(value = "/methodPost",method = RequestMethod.POST)
    public Object method(String params)
    {
        return "ok";
    }

    //无参测试
    @RequestMapping("/hello")
    public Object hello()
    {
        return "HelloTest";
    }
    //带参测试
    @RequestMapping("/params")
    public Object params(String params)
    {
        return params;
    }
    //多参测试
    @RequestMapping("/twoparams")
    public Object twoParams(String params,int time)
    {
        String response = "";
        for(int i=0;i<time;i++)
        {
            response = response+params;
        }
        return response;
    }
}
