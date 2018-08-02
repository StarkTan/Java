package com.stark.java8;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int caloaries;
    private final Type type;

    public Dish(String name, boolean vegetarian, int caloaries, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.caloaries = caloaries;
        this.type = type;
    }

    public int getCaloaries() {
        return caloaries;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public enum Type {MEAT, FISH, OTHER}

    @Override
    public String toString() {
        return name;
    }

    public static List<Dish> getDishs() {
        List<Dish> dishes = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH));
        return dishes;
    }
}
