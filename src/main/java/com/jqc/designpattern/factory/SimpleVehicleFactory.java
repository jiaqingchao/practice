package com.jqc.designpattern.factory;

/**
 * 简单工厂的可扩展性不好
 */
public class SimpleVehicleFactory {
    public Car createCar() {
        //before proceesing
        return new Car();
    }

    public Plane createPlane() {
        //before proceesing
        return new Plane();
    }

    public Broom createBroom() {
        //before proceesing
        return new Broom();
    }
}
