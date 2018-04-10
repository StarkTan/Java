package com.stark.design23.command;

/**
 * Created by Stark on 2018/3/27.
 * 实现Command 接口的灯 执行命令的实体
 */
public class Light implements Command {

    @Override
    public void excute() {
        System.out.println("LIGHT ON");
    }

    @Override
    public void undo() {
        System.out.println("LIGHT OFF");
    }
}
