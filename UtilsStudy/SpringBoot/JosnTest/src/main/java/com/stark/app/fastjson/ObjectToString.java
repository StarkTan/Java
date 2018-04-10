package com.stark.app.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import com.stark.entity.jsontest.EntityManage;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Strak on 2017/5/14.
 * 测试实体类转换成json字符串
 */
public class ObjectToString
{

    public static void main(String[] args)
    {
        testCustomFilter();
    }

    //自定义过滤器
    private static void testCustomFilter()
    {
        Object object = EntityManage.getMainObject();
        String[] excluds = {"/cycle/id/**","/cycle/object/object/**"};
        CustomFilter filter = new CustomFilter(excluds);
        String jsonString = JSONObject.toJSONString(object,filter);
        System.out.println(jsonString);

    }
    //过滤数据，可以封装filter。按照属性过滤pre最好，按照值过滤使用filter
    private static void testSimpleFilter()
    {
        Object object = EntityManage.getMainObject();
        PropertyPreFilter filter = new PropertyPreFilter()
        {
            @Override
            public boolean apply(JSONSerializer jsonSerializer, Object o, String s)
            {
                System.out.println(jsonSerializer.toString());
                if(s.equals("object"))
                {
                    return false;
                }
                return true;
            }
        };
        String jsonString = JSONObject.toJSONString(object,filter);
        System.out.println(jsonString);

    }
    //解析复杂数据
    private static void testMainObject()
    {
        Object object = EntityManage.getMainObject();

        String jsonString = JSONObject.toJSONString(object);
        System.out.println(jsonString);
    }
    //测试List解析，初步测试循环侦测
    private static void testList()
    {
        List list = Arrays.asList(EntityManage.getSimple(),EntityManage.getSimple(),EntityManage.getCycleOne());
        String jsonString = JSONObject.toJSONString(list);
        System.out.println(jsonString);
    }
    //测试转换循环对象
    private static  void testCycle()
    {
        Object objectone = EntityManage.getCycleOne();
        Object objecttwo = EntityManage.getCycleTwo();
        String jsonString = JSONObject.toJSONString(objectone);
        System.out.println(jsonString);
        jsonString = JSONObject.toJSONString(objecttwo);
        System.out.println(jsonString);
    }
    //简单的对象转jsonString
    private static void testSimple()
    {
        Object simple = EntityManage.getSimple();
        String jsonString = JSONObject.toJSONString(simple);
        System.out.println(jsonString);
    }
}
