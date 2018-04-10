package com.stark.design23.bridge;

/**
 * Created by Stark on 2018/1/17.
 * 具有展示数据和修改数据功能的产品
 */
public class Product {

    private DataBase dataBase;

    public Product(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public String display() {
        return dataBase.read();
    }

    public void update(String msg) {
        //先存储写数据
        dataBase.write(msg);
    }
}
