package com.stark.lock;

import java.util.concurrent.locks.Lock;

public class SynchronizedTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final char num = (char) ('A' + i);
            new Thread(() -> {
                System.out.println("Thread " + num + " started");
                synchronized (Lock.class) {
                    System.out.println("Thread " + num + " required synchronized Lock!");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread " + num + " release synchronized Lock!");
                }
            }).start();
        }
    }
}
