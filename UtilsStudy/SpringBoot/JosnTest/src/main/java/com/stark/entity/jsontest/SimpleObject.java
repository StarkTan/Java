package com.stark.entity.jsontest;

import java.io.Serializable;

/**
 * Created by Strak on 2017/5/14.
 * 简单的实体类
 */
public class SimpleObject implements Serializable
{
    private String id;
    private String name;

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
}
