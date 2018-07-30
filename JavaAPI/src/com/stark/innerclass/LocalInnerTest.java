package com.stark.innerclass;

/**
 * 局部内部类
 * 1.定义在方法体，甚至比方法体更小的代码块中
 * 2.类比局部变量。
 * 3.局部内部类是所有内部类中最少使用的一种形式。
 * 4.局部内部类可以访问的外部类的成员根据所在方法体不同。
 * 如果在静态方法中：
 * 可以访问外部类中所有静态成员，包含私有
 * 如果在实例方法中：
 * 可以访问外部类中所有的成员，包含私有。
 * 局部内部类可以访问所在方法中定义的局部变量，但是要求局部变量不会被修改（通常使用final 关键字）。
 * 5.局部类内部类不能定义除了常量以外静态成员
 */

public class LocalInnerTest {

    public static void main(String[] args) {
        LocalOuter.test1();
        new LocalOuter().test2();
    }
}

class LocalOuter {
    private static String s1 = "outer static member";
    private String s3 = "outer member";

    public static void test1() {
        String s2 = "outer static fuction member";
        class LocalInner {
            private /*static*/ String s5 = "innner member";
            private /*static*/ void print() {
                System.out.println(s1);
                System.out.println(s2);
                System.out.println(s5);
            }
        }
        new LocalInner().print();
    }

    public void test2() {
        final String s2 = "outer fuction member";
        String s4 = "outer fuction member no final";
        class LocalInner {
            private /*static*/ String s5 = "innner member";
            private void print() {
                System.out.println(s1);
                System.out.println(s2);
                System.out.println(s3);
                System.out.println(s4);
                System.out.println(s5);
            }
        }
        new LocalInner().print();
    }

}
