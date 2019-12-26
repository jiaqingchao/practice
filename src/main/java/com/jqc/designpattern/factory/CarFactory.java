package com.jqc.designpattern.factory;

public class CarFactory {
    private CarFactory() {
    }

    public static Moveable create() {
        System.out.println(" a car created");
        return new Car();
    }
}
