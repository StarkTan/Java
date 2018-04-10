package com.stark.design23.interpret;

/**
 * Created by Stark on 2018/3/27.
 * 变量 终结符表达式
 */
public class Variable extends Expression {

    private String name;

    public Variable(String name){
        this.name = name;
    }
    @Override
    public boolean equals(Object obj) {

        if(obj != null && obj instanceof Variable)
        {
            return this.name.equals(
                    ((Variable)obj).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int interpret(Context ctx) {
        return ctx.lookup(this);
    }

}

