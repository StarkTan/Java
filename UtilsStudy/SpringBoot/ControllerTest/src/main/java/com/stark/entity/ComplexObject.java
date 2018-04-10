package com.stark.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Strak on 2017/5/14.
 * 复杂的对象，包含基本属性，包含对象，包含集合
 */
public class ComplexObject implements Serializable
{
    public ComplexObject()
    {
        /*//不够报异常
        set.add(new SetObject());
        set.add(new SetObject());
        set.add(new SetObject());*/
    }

    private String name;
    private SingleObject single;
    private Set<SetObject> set = new HashSet<SetObject>();
    private List<ListObject> list;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public SingleObject getSingle()
    {
        return single;
    }

    public void setSingle(SingleObject single)
    {
        this.single = single;
    }

    public Set getSet()
    {
        return set;
    }

    public void setSet(Set set)
    {
        this.set = set;
    }

    public List<ListObject> getList()
    {
        return list;
    }

    public void setList(List<ListObject> list)
    {
        this.list = list;
    }
}
