package com.stark.example2;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Stark on 2017/7/13.
 */
public class App1 {
    public static void main(String[] args) throws InterruptedException, TimeoutException, IOException {
        new Consumer().getConsum();
    }
}
