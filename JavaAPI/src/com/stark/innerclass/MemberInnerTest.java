package com.stark.innerclass;

import com.stark.innerclass.MerberOuter.MerberInner;

/**
 * 成员内部类
 * 1.没有使用static修饰的内部类。
 * 2.在成员内部类中不允许出现静态变量和静态方法的声明。
 * static只能用在静态常量的声明上。
 * 3.成员内部类中可以访问外部类中所有的成员(变量，方法)，
 * 包含私有成员，如果在内部类中定义有和外部类同名的实例变量，访问：OuterClass.this.outerMember;
 * 4.构建内部类的实例，要求必须外部类的实例先存在
 * 外部类的外部/外部类的静态方法：new Outer().new Inner();
 * 外部类的实例方法：
 * new Inner();
 * this.new Inner();
 * <p>
 * 应用场景：每一个外部类对象都需要一个内部类的实例，内部类离不开外部类存在
 * 应用例子：Netty中的Channel
 */
public class MemberInnerTest {
    public static void main(String[] args) {
        new MerberOuter().new MerberInner().print();
        new MerberInnerSon(new MerberOuter()).print();
    }
}

class MerberOuter {
    private String s1 = "outer s1";

    private void print() {
        System.out.println("outer print");
        System.out.println(new MerberInner());
    }

    class MerberInner {
        private final static String name = "Inner";
        //属性和方法不能加 static
        private /*static*/ String s1 = "inner s1";

        public  /*static*/ void print() {
            System.out.println("inner print");
            System.out.println(s1);
            // 调用与外部类相同名称的属性
            System.out.println(MerberOuter.this.s1);
            MerberOuter.this.print();
        }

    }
}

//继承时 空间名要写全，构造函数要加入外部类实例
class MerberInnerSon extends MerberOuter.MerberInner {
    public MerberInnerSon(MerberOuter outer) {
        outer.super();
    }
}

