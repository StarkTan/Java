package com.stark.controller.controller;

import com.stark.common.JsonExclude;
import com.stark.common.TestRequest;
import com.stark.common.TestResponse;
import com.stark.entity.SimpleObject;
import com.stark.entity.SingleObject;
import com.stark.entity.jsontest.EntityManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Strak on 2017/5/16.
 * 用来测试自定义参数解析器和自定义返回值
 */
@Controller
@RequestMapping("/custom")
public class CustomController
{
    @InitBinder("simple")
    public void initBinderSimple(WebDataBinder binder)
    {
        binder.setFieldDefaultPrefix("simple.");
    }
    @InitBinder("single")
    public void initBinderSingle(WebDataBinder binder)
    {
        binder.setFieldDefaultPrefix("single.");
    }

    @RequestMapping("/binder")
    @ResponseBody
    public Object testBinderRequest(@ModelAttribute("simple")SimpleObject simple,
                                    @ModelAttribute("single")SingleObject single)
    {
        return simple.toString()+','+single.toString();
    }


    @RequestMapping("/request")
    @ResponseBody
    public Object testCustomRequest(@TestRequest Object object)
    {
        return object;
    }

    @RequestMapping("/response")
    @TestResponse
    public Object testCustomResponse(@TestRequest Object object)
    {
        return object;
    }


    @RequestMapping("/jsonreturn")
    @JsonExclude(excludes = {"/**/age/**"})
    public Object testCustomJson(@TestRequest Object object)
    {
        return object;
    }

    @RequestMapping("/jsonreturntwo")
    @JsonExclude(excludes = {"/**/object/object","/**/list/id"})
    public Object testCustomJsonTwo()
    {
        Object mainObject = EntityManage.getMainObject();
        return mainObject;
    }
}
