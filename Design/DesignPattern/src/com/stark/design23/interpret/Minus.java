package com.stark.design23.interpret;

/**
 * Created by Stark on 2018/3/27.
 * 减法 非终结符表达式
 */
public class Minus extends Expression {

    private Expression left, right;

    public Minus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Minus) {
            return left.equals(((Minus) obj).left) && right.equals(((Minus) obj).right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public int interpret(Context ctx) {

        return left.interpret(ctx) - right.interpret(ctx);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " - " + right.toString() + ")";
    }
}
