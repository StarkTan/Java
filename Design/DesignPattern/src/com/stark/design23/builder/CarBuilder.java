package com.stark.design23.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stark on 2018/1/26.
 * 构建者，这里面维护车的创建逻辑
 */
public class CarBuilder {

    private static Car getCar() {
        Car car = new Car();
        List<Tyre> tyres = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            car.setTyres(tyres);
        }
        car.setBoby(new Boby());
        return car;
    }
}
