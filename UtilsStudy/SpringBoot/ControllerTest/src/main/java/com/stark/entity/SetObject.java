package com.stark.entity;

import java.io.Serializable;

/**
 * Created by Strak on 2017/5/14.
 * 集合对象
 */
public class SetObject implements Serializable
{
    private String name;
    private Integer number;

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
