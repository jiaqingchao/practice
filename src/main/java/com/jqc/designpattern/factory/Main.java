package com.jqc.designpattern.factory;

public class Main {
    public static void main(String[] args) {
        Moveable m = CarFactory.create();
        m.go();
    }
}
