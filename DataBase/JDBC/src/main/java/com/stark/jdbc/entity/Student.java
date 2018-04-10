package com.stark.jdbc.entity;

import java.util.UUID;

/**
 * Created by Strak on 2017/4/22.
 * 测试使用实体类
 */
public class Student
{
    private String id = UUID.randomUUID().toString().replace("-",""); //使用uuid
    private String name;
    private String number;
    private Integer age;
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

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }
}
