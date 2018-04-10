package com.stark.design23.template;

/**
 * Created by Stark on 2018/3/26.
 */
public class System_B extends AbsSalarySystem {
    @Override
    public void count() {
        System.out.println("通过工作量计算薪水");
    }

    @Override
    public void give() {
        System.out.println("工资发现金");
    }
}
