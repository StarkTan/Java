package com.stark.design23.composite;

/**
 * Created by Stark on 2018/1/17.
 * B类员工
 */
public class EmpB extends Emp {
    public EmpB(String name) {
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
