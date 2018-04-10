package com.stark.design23.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stark on 2018/1/17.
 * A员工类，管理EmpB员工
 */
public class EmpA extends Emp {
    public EmpA(String name) {
        super(name);
    }

    public void work() {
        System.out.println(this.name + " begin work!");
    }

    @Override
    public void add(Emp emp) {
        this.emps.add(emp);
    }
}
