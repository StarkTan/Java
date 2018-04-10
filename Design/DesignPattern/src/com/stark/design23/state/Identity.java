package com.stark.design23.state;

/**
 * Created by Stark on 2018/3/26.
 * 进行操作的类，持有status对象
 */
public class Identity {
    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }

    public void doAction() {
        System.out.println(status.doAction());
    }
}
