package com.stark.design23.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stark on 2018/1/26.
 * 画笔工厂，用以获取画笔
 * 如果没有就进行创建保存，如果有就直接返回
 */
public class PencilFactory {

    private static Map<String, Pencil> cache = new HashMap<>();

    public static Pencil getPencil(String color) {
        if (cache.containsKey(color)) {
            return cache.get(color);
        } else {
            Pencil pencil = new PencilImp(color);
            cache.put(color, pencil);
            return pencil;
        }
    }
}
