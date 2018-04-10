package com.stark.design23.facade;

/**
 * Created by Stark on 2018/1/16.
 * 外观类
 */
public class Facade {
    public int handle(int begin, int end) {
        Minus minus = new Minus();
        Plus plus = new Plus();
        int res = begin++;
        while (begin <= end) {
            res = plus.plus(res, begin++);
            if (begin > end) break;
            res = minus.minus(res, begin++);
        }
        return res;
    }
}
