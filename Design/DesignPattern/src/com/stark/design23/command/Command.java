package com.stark.design23.command;

/**
 * Created by Stark on 2018/3/27.
 * 命令接口 具体实现的操作
 */
public interface Command {
    void excute();

    void undo();
}
