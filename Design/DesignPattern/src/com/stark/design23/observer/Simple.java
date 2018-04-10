package com.stark.design23.observer;

/**
 * Created by Stark on 2018/1/21.
 * 简单实现
 */
public class Simple {

    static class ClassA {
        private ClassB classB = new ClassB();

        public void getData(String data) {
            classB.print(data);
        }
    }

    static class ClassB {
        public void print(String data) {
            System.out.println(data);
        }
    }

    public static void main(String[] args) {
        ClassA classA = new ClassA();
        classA.getData("xxxx");
    }
}
