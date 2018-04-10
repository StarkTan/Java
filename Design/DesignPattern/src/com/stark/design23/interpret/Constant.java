package com.stark.design23.interpret;

/**
 * Created by Stark on 2018/3/27.
 * 常量 终结符表达式
 */
public class Constant extends Expression {

    private int value;

    public Constant(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof Constant) {
            return this.value == ((Constant) obj).value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public int interpret(Context ctx) {

        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}

