package com.stark.design23.command;

/**
 * Created by Stark on 2018/3/27.
 * 测试
 */
public class App {
    public static void main(String[] args) {
        MiPhone miPhone = new MiPhone();
        //设置手机的4个按钮 分别表示灯的开、关 电视的开、关
        miPhone.setBtn(new Light());
        miPhone.setBtn(new TV());
        miPhone.pushBtn(0);
        miPhone.pushBtn(1);
        miPhone.pushBtn(2);
        miPhone.pushBtn(3);
    }
}
