package com.stark.entity.jsontest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Strak on 2017/5/14.
 * 返回需要创建的对象
 */
public class EntityManage
{

    public static Object getSimple()
    {
        SimpleObject object = new SimpleObject();
        object.setName("simple");
        object.setId("simple_object");
        return object;
    }

    public static Object getCycleOne()
    {
        CycleObject object = new CycleObject();
        object.setName("outer");
        object.setId("cycle1");
        object.setObject(object);
        return object;
    }

    public static Object getCycleTwo()
    {
        CycleObject object = new CycleObject();
        object.setName("outer");
        object.setId("cycle1");
        CycleObject object2 = new CycleObject();
        object2.setName("inner");
        object2.setId("cycle2");
        object.setObject(object2);
        object2.setObject(object);
        return object;
    }
    public static Object getMainObject()
    {
        List<SimpleObject> list = new ArrayList<SimpleObject>();
        SimpleObject list1 = new SimpleObject();
        list1.setName("list1");
        list1.setId("simple1");
        SimpleObject list2 = new SimpleObject();
        list2.setName("list2");
        list2.setId("simple2");
        list.add(list1);
        list.add(list2);
        MainObject object = new MainObject();
        object.setId("main");
        object.setName("mainObject");
        object.setSingle((SimpleObject) getSimple());
        object.setCycle((CycleObject) getCycleTwo());
        object.setList(list);
        return object;
    }

}
