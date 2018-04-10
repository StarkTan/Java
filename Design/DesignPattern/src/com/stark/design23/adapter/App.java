package com.stark.design23.adapter;

import java.lang.annotation.Target;

/**
 * 测试
 */
public class App {
    public static void main(String[] args) {
        Animal cat = new CatAdapter();
        Animal dog = new DogAdapter();
        cat.call();
        dog.call();
    }
}
