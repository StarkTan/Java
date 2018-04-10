package com.stark.design23.builder;

import java.util.List;

/**
 * Created by Stark on 2018/1/26.
 * 车
 */
public class Car {
    private List<Tyre> tyres;
    private Boby boby;


    public List<Tyre> getTyres() {
        return tyres;
    }

    public void setTyres(List<Tyre> tyres) {
        this.tyres = tyres;
    }

    public Boby getBoby() {
        return boby;
    }

    public void setBoby(Boby boby) {
        this.boby = boby;
    }
}
