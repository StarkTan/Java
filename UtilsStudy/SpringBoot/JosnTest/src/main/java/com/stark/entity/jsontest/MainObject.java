package com.stark.entity.jsontest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Strak on 2017/5/14.
 * json转换对象，包含自身基本属性、单个对象、循环对象、List对象
 */
public class MainObject implements Serializable
{
    private String id;
    private String name;
    private SimpleObject single;
    private CycleObject cycle;
    private List<SimpleObject> list;

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

    public SimpleObject getSingle()
    {
        return single;
    }

    public void setSingle(SimpleObject single)
    {
        this.single = single;
    }

    public CycleObject getCycle()
    {
        return cycle;
    }

    public void setCycle(CycleObject cycle)
    {
        this.cycle = cycle;
    }

    public List<SimpleObject> getList()
    {
        return list;
    }

    public void setList(List<SimpleObject> list)
    {
        this.list = list;
    }
}
