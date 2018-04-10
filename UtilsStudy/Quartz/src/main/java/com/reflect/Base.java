package com.reflect;

import java.util.Date;

/**
 * Created by Stark on 2018/1/8.
 * 目标类
 */
@Anno(name = "Target Class")
public class Base extends SupBase<String> implements IBase<Object> {
    @Anno(name = "callPri")
    private void callPri(@Anno(name = "param date") Date date) {
        System.out.println("I am SubObject Private " + date);
    }

    public void callPub(Date date) {
        System.out.println("I am SubObject Public" + date);
    }
    @Anno(name="filed base")
    private String namePri = "Class Private Property";
    public String namePub = "Class Public Property";
}
