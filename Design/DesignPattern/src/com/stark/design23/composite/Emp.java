package com.stark.design23.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stark on 2018/1/17.
 * 抽象类
 */
public abstract class Emp {
    protected String name;

    protected List<Emp> emps = new ArrayList<Emp>();

    protected Emp(String name) {
        this.name = name;
    }

    public abstract void work();

    public abstract void add(Emp emp);
}
