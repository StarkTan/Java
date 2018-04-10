package com.stark.design23.prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试
 */
public class App {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Prototype primary = new Prototype();
        List<String> strs = new ArrayList<>();
        strs.addAll(Arrays.asList("one", "two", "three"));
        primary.setName("primary");
        primary.setList(strs);
        System.out.println(primary);

        //浅复制
        Object simpleClone = primary.clone();
        System.out.println(simpleClone);
        strs.remove("one");
        System.out.println(simpleClone);

        //深复制
        //将对象字节化输出到字节数组中
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(outputStream);
        output.writeObject(primary);
        byte[] bytes = outputStream.toByteArray();
        //将对象从字节数组中实例化出来
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream input = new ObjectInputStream(inputStream);
        Object deepClone = input.readObject();
        System.out.println(deepClone);
        strs.add("one");
        System.out.println(simpleClone);
        System.out.println(deepClone);
    }
}
