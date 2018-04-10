package com.stark.design23.flyweight;

/**
 * Created by Stark on 2018/1/26.
 * 画笔实现类
 */
public class PencilImp implements Pencil {

    private String color;

    public PencilImp(String color) {
        System.out.println(color+" pencil was created");
        this.color = color;
    }
    @Override
    public void draw() {
        System.out.println("I draw " + color);
    }
}
