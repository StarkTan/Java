package com.stark.design23.observer;

/**
 * Created by Stark on 2018/1/21.
 * 观察者的接口
 */
public interface Observer {
    void addListener(Listener listener);

    void removeListener(Listener listener);

    void notifyListener(String data);
}
