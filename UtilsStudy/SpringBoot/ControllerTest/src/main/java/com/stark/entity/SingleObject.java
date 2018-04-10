package com.stark.entity;

import java.io.Serializable;

/**
 * Created by Stark on 2017/5/14.
 * 用于包含对象
 */
public class SingleObject implements Serializable
{
    private String name;
    private Integer number;

    @Override
    public String toString()
    {
        return "I'm Single named "+name+" number is "+number;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number = number;
    }
}
