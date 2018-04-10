package com.stark.design23.interpret;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stark on 2018/3/27.
 * 环境角色 上下文
 */
public class Context {

    private Map<Variable, Integer> map = new HashMap<Variable, Integer>();

    public void assign(Variable var, int value) {
        map.put(var, value);
    }

    public int lookup(Variable var) throws IllegalArgumentException {
        Integer value = map.get(var);
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return value;
    }
}

