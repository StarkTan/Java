package com.stark.design23.composite;

/**
 * Created by Stark on 2018/1/17.
 * 测试
 */
public class App {
    public static void main(String[] args) {
        EmpA a001 = new EmpA("001");
        EmpB b002 = new EmpB("002");
        EmpB b003 = new EmpB("003");
        a001.add(b002);
        a001.add(b003);
        //工作
        a001.work();
        b002.work();
    }
}
