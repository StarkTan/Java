package com.stark.logger;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Stark on 2017/10/18.
 */
public class App {
    public static void main(String[] args) {
        /*new Test1().test();
        new Test2().test();*/
        try (OutputStream outputStream = new FileOutputStream("D:/xxxx")) {
            outputStream.write(12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
