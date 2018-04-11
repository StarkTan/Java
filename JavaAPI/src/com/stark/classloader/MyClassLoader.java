package com.stark.classloader;

import sun.util.resources.LocaleData;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Created by Stark on 2018/4/11.
 * 自定义ClassLoader
 */


public class MyClassLoader extends ClassLoader {
    //将二进制文件流传递给父类
    public Class<?> findClass(String fileName) throws ClassNotFoundException {
        File classFile = new File(fileName);
        if (!classFile.exists()) {
            throw new ClassNotFoundException(classFile.getPath() + " 不存在");
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ByteBuffer bf = ByteBuffer.allocate(1024);
            FileInputStream fis = null;
            FileChannel fc = null;

            try {
                fis = new FileInputStream(classFile);
                fc = fis.getChannel();

                while (fc.read(bf) > 0) {
                    bf.flip();
                    bos.write(bf.array(), 0, bf.limit());
                    bf.clear();
                }
            } catch (IOException var21) {
                var21.printStackTrace();
            } finally {
                try {
                    assert fis != null;
                    fis.close();
                    assert fc != null;
                    fc.close();
                } catch (IOException var19) {
                    var19.printStackTrace();
                }

            }

            return this.defineClass(null, bos.toByteArray(), 0, bos.toByteArray().length);
        }
    }
}
