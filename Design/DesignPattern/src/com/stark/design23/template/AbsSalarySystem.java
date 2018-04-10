package com.stark.design23.template;

/**
 * Created by Stark on 2018/3/26.
 * 抽象类：公司的薪水系统
 */
public abstract class AbsSalarySystem {
    public abstract void count();

    public abstract void give();

    public final void pay() {
        count();
        give();
    }

}
