package com.stark.design23.interpret;

/**
 * Created by Stark on 2018/3/27.
 * 加法 非终结符表达式
 */
public class Plus extends Expression {

    private Expression left, right;

    public Plus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Plus) {
            return left.equals(((Plus) obj).left) &&
                    right.equals(((Plus) obj).right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public int interpret(Context ctx) {

        return left.interpret(ctx) + right.interpret(ctx);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }

}