package com.jqc.designpattern.abstractFactory;

import com.jqc.designpattern.factory.CarFactory;
import com.jqc.designpattern.factory.Moveable;

public class Main {
    public static void main(String[] args) {
        AbstractFactory f = new ModernFactory();
        Vehicle c = f.createVehicle();
        c.go();
        Weapon w = f.createWeapon();
        w.shoot();
        Food b = f.createFood();
        b.printName();
    }
}
