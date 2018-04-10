package com.stark.design23.prototype;

import java.io.Serializable;
import java.util.List;

/**
 * 原型 实现Cloneable接口就进行浅复制
 * 使用 序列化接口将原型使用二进制进行转换
 */
public class Prototype implements Cloneable, Serializable {
    private String name;
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "name: " + name + ";\nlist:" + list.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
