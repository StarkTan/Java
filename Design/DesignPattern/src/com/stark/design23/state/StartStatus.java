package com.stark.design23.state;

/**
 * Created by Stark on 2018/3/26.
 *
 */
public class StartStatus implements Status {
    @Override
    public String doAction() {
        return "start status";
    }
}
