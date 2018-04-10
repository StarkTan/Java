package com.stark.design23.interpret;

/**
 * Created by Stark on 2018/3/27.
 * 测试使用
 */
public class App {
    public static void main(String[] args) {
        Context ctx = new Context();
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Constant c = new Constant(1);
        ctx.assign(x, 2);
        ctx.assign(y, 3);

        Expression exp = new Plus(new Plus(c, x), new Minus(y, x));
        System.out.println(exp.toString() + "=" + exp.interpret(ctx));
    }
}
