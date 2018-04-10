package com.stark.design23.flyweight;

/**
 * Created by Stark on 2018/1/26.
 * 测试
 */
public class App {
    public static void main(String[] args) {
        String[] strings = new String[]{"blue", "red", "blue", "yellow", "red"};
        for (String str : strings) {
            PencilFactory.getPencil(str).draw();
        }
    }
}
