package com.stark.controller.controller;

import com.stark.entity.SimpleObject;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Strak on 2017/5/13.
 * 测试简单的对象接受
 */
@RestController
@RequestMapping("/object")
public class ObjectController
{

    @RequestMapping("/path_variable/{name}/{age}/{sex}")
    public Object testPathVariable(@PathVariable("name") String name,
                                   @PathVariable("age") Integer age,
                                   @PathVariable(value = "sex",required = false) boolean sex)
    {
        SimpleObject object = new SimpleObject();
        object.setName(name);
        object.setAge(age);
        object.setSex(sex);
        return object.toString();
    }


    @RequestMapping("/simple")
    public Object testSimple(@ModelAttribute SimpleObject object)
    {
        return object.toString();
    }

    @RequestMapping("/simplemix")
    public Object testMix(@ModelAttribute SimpleObject object,
                          String flag,String name)
    {
        return object.toString()+",flag:"+flag+",name:"+name;
    }

    @RequestMapping("/simpletwoobj")
    public Object testTwoObj(@ModelAttribute SimpleObject object1,
                             @ModelAttribute SimpleObject object2)
    {
        return object1.toString()+','+object2.toString();
    }

    @RequestMapping("/jsonobj")
    public Object testJsonObj(@RequestBody SimpleObject object)
    {
        return object.toString();
    }

    @RequestMapping("/json_mix_primite")
    public Object testJsonObjMix(@RequestBody SimpleObject object,
                                 @RequestParam String flag)
    {
        return object.toString()+",flag:"+flag;
    }

    @RequestMapping("/json_mix_obj")
    public Object testJsonObjMixTwo(@RequestBody SimpleObject object1,
                                 @ModelAttribute SimpleObject object2)
    {
        return object1.toString()+','+object2.toString();
    }
}
