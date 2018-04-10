package com.stark;

/**
 * Created by Stark on 2017/10/30.
 * -Xss128k //设置栈的容量 没有见到OOM 全是SOF 设置越小则栈的深度越小
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try{
            oom.stackLeak();
        }catch (Throwable e){
            System.out.println(oom.stackLength);
            throw e;
        }
    }
}
