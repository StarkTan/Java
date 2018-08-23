package com.stark.lock;

import java.sql.SQLOutput;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();

        for (int i = 0; i < 10; i++) {
            final int I = i;
            new Thread(() -> {
                if (I % 2 == 0) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                readLock.lock();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readLock.unlock();
            }).start();
        }
        Thread.sleep(1000);
        new Thread(() -> {
            writeLock.lock();
            System.out.println("write lock be locked");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
        }).start();

        for (int i = 0; i < 10; i++) {
            System.out.println("==============");
            System.out.println(((ReentrantReadWriteLock) readWriteLock).getReadLockCount());
            System.out.println(((ReentrantReadWriteLock) readWriteLock).getReadHoldCount());
            System.out.println(((ReentrantReadWriteLock) readWriteLock).getWriteHoldCount());
            System.out.println(((ReentrantReadWriteLock) readWriteLock).getQueueLength());
            Thread.sleep(2000);
        }
    }


}
