package com.stark.design23.state;

/**
 * Created by Stark on 2018/3/26.
 */
public class StopStatus implements Status {

    @Override
    public String doAction() {
        return "stop status";
    }
}
