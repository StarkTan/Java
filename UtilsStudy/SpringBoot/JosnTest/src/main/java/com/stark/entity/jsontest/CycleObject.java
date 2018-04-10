package com.stark.entity.jsontest;

import java.io.Serializable;

/**
 * Created by Strak on 2017/5/14.
 * 一个循环类，json转换时会无限循环
 */
public class CycleObject implements Serializable
{
    private String id;
    private String name;
    private CycleObject object;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public CycleObject getObject()
    {
        return object;
    }

    public void setObject(CycleObject object)
    {
        this.object = object;
    }
}
