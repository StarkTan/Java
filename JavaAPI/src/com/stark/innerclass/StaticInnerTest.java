package com.stark.innerclass;

/**
 * 静态内部类
 * 1.声明在类体部，方法体外，并且使用static修饰的内部类
 * 2.访问特点可以类比静态变量和静态方法
 * 3.脱离外部类的实例独立创建
 * 在外部类的外部构建内部类的实例
 * new Outer.Inner();
 * 在外部类的内部构建内部类的实例
 * new Inner();
 * 4.静态内部类体部可以直接访问外部类中所有的静态成员，包含私有
 * <p>
 * 用途：多用于单例模式
 */
public class StaticInnerTest {
    public static void main(String[] args) {
        System.out.println("test");
        StaticOuter.StaticInner.staticPrint(); //在使用内部类的时候才会进行加载
        new StaticOuter.StaticInner().print(); //实例化内部类时不需要实例化外部类
        new StaticOuter().print();
    }
}

class StaticOuter {
    private String s1 = "s1";
    private static String s2 = "s2";

    private static void sattic_print() {
        System.out.println("outer");
    }

    public void print() {
        //可以直接实例化
        StaticInner innerClass = new StaticInner();
        innerClass.print();
    }

    public static class StaticInner {
        static {
            System.out.println("inner class load");
        }

        public static void staticPrint() {
            StaticOuter.sattic_print();
        }

        public void print() {
            //System.out.println(s1);
            System.out.println(s2);
        }
    }
}
