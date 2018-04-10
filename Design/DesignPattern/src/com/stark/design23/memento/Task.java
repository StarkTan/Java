package com.stark.design23.memento;

/**
 * Created by Stark on 2018/3/26.
 * 需要被保存状态的类
 */
public class Task {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Status savaStatus() {
        return new Status(this.status);
    }

    public void setStutasfromCache(Status stutas) {
        this.status = stutas.getStatus();
    }


}
