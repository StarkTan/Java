package com.reflect;

import java.util.Date;

/**
 * Created by Stark on 2018/1/8.
 * 父类
 */
@Anno(name = "SupClass")
public class SupBase<T extends Object> {
    private String nameSupPri = "SupClass Private Property";
    public String nameSupPub = "SupClass Public Property";
    private void callSupPri(Date date) {
        System.out.println("I am SupBase Private " + date);
    }
    public void callSupPub(Date date) {
        System.out.println("I am SupBase Public " + date);
    }
}
