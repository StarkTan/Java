package com.stark.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            System.out.println("Thread   A started");
            lock.lock();
            System.out.println("Thread A required synchronized Lock!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread A release synchronized Lock!");
            lock.unlock();
            System.out.println("Thread   A end");
        }).start();
        new Thread(() -> {
            System.out.println("Thread   B started");
            lock.lock();
            System.out.println("Thread B required synchronized Lock!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread B release synchronized Lock!");
            lock.unlock();
            System.out.println("Thread   B end");
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            System.out.println("Thread   C started");
            boolean b = lock.tryLock();
            if (b) {
                System.out.println("Thread C required synchronized Lock!");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread C release synchronized Lock!");
                lock.unlock();
            } else {
                System.out.println("Thread   C give up requiring lock");
            }
            System.out.println("Thread   C end");
        }).start();
        Thread thread_D = new Thread(() -> {
            System.out.println("Thread   D started");
            boolean b = false;
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("Thread   D interrupt");
                return;
            }
            System.out.println("Thread D required synchronized Lock!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread D release synchronized Lock!");
            lock.unlock();
            System.out.println("Thread   D end");
        });
        thread_D.start();
        Thread.sleep(5000);
        thread_D.interrupt();
        System.out.println("main thread end!");
    }
}
