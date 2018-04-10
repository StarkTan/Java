package com.stark.entity;

/**
 * Created by Strak on 2017/5/13.
 * 用于简单实体类的实现
 */
public class SimpleObject
{
    private String name;
    private Integer age;
    private boolean sex;

    @Override
    public String toString()
    {
        return "name:"+name+",age:"+age+",boy:"+(sex?"yes":"no");
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public boolean isSex()
    {
        return sex;
    }

    public void setSex(boolean sex)
    {
        this.sex = sex;
    }
}
