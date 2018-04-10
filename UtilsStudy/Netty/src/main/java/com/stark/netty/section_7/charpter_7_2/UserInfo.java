package com.stark.netty.section_7.charpter_7_2;

import org.msgpack.annotation.Message;

/**
 * Created by Stark on 2018/3/8.
 * POJO 对象
 */
@Message
public class UserInfo {
    private int age;
    private String name;

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "[ " + name + ", " + age + "]";
    }
}
