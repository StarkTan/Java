package com.stark.design23.state;

/**
 * Created by Stark on 2018/3/26.
 * 测试
 */
public class App {
    public static void main(String[] args) {
        Identity identity = new Identity();
        Status status = new StartStatus();
        status.change(identity);
        identity.doAction();
        status = new StopStatus();
        status.change(identity);
        identity.doAction();
    }
}
