package com.stark.design23.template;

/**
 * Created by Stark on 2018/3/26.
 */
public class System_A extends AbsSalarySystem {
    @Override
    public void count() {
        System.out.println("通过工时计算薪水");
    }

    @Override
    public void give() {
        System.out.println("工资发到银行卡");
    }
}
