package com.stark.java8;

public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.value = value;
        this.year = year;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getValue() {
        return value;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "("+this.trader+", year: "+this.year+", value: "+this.value+")";
    }
}
