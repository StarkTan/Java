package com.stark.design23.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Stark on 2018/1/21.
 * 使用一个类实现观察者和被观察者接口
 */
public class ObserverImp implements Observer, Subject {

    Set<Listener> listeners = new HashSet<>();

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListener(String data) {
        for (Listener listener : listeners) {
            listener.notified(data);
        }
    }

    @Override
    public void update(String string) {
        notifyListener(string);
    }
}
