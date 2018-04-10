package com.stark.reflect.four;

/**
 * Created by HP on 2017/3/31.
 */
public class User
{
    private String name;
    private Integer age;
    private boolean male;

    public User() //构造参数应该设置成public，不过private单例模式使用private
    {
        super();
    }

    public User(String name) //构造参数应该设置成public，不过private单例模式使用private
    {
        super();
        this.name = name;
    }

    public User(String name,boolean male)
    {
        super();
        this.name = name;
        this.male = male;
    }

    private  User(String name,Integer age,boolean male)
    {
        super();
        this.name = name;
        this.male = male;
        this.age = age;
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

    public boolean isMale()
    {
        return male;
    }

    public void setMale(boolean male)
    {
        this.male = male;
    }

    @Override
    public String toString()
    {
        return "名字： "+name+"; 年龄： "+age+"; 性别："+(male?"男":"女");
    }
}
