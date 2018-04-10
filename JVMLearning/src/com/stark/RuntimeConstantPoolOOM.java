package com.stark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stark on 2017/10/30.
 * 方法区和运行常量池溢出
 * -XX:PermSize=10M  -XX:MaxPermSize=10M
 * 这个在1.7开始逐步去永久代之后就没有再出现过了
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i=0;
        while (true){
            list.add(String.valueOf(i++).intern());// intern native方法 将字符串添加到常量池
        }
    }
}
