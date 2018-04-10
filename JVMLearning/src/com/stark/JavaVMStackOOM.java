package com.stark;

/**
 * Created by Stark on 2017/10/30.
 * -Xss2M 线程会去分配jvm进程的空间，当没有空间可以分配给线程的时候会出现OOM
 * MD 我的64位机器什么时候报错，好怕
 * 嗯，一个启动我休息了半个小时
 */
public class JavaVMStackOOM {
    public void dontStop() {
        while (true) {

        }
    }
    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }

    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
