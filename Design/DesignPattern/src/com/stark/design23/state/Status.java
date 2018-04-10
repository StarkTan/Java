package com.stark.design23.state;

/**
 * Created by Stark on 2018/3/26.
 * 状态接口，有change方法和doAction方法
 */
public interface Status {

    default void change(Identity identity) {
        identity.setStatus(this);
    }

    String doAction();
}
