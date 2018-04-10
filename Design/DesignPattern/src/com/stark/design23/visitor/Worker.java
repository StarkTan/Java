package com.stark.design23.visitor;

/**
 * Created by Stark on 2018/3/28.
 * 接受自己的接口
 */
public interface Worker {

    void accept(Visitor visitor);
}
