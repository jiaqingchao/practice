package com.jqc.designpattern.observer.v4;

class Child {
    private boolean cry = false;
    private Dad dad = new Dad();
    private Mum mum = new Mum();
    private Dog dog = new Dog();

    public boolean isCry() {
        return cry;
    }

    public void wakeUp() {
        cry = true;
        dad.feed();
        mum.hug();
        dog.wang();
    }
}

class Dad {
    public void feed() {
        System.out.println("Dad feeding...");
    }
}

class Mum {
    public void hug() {
        System.out.println("Mum hugging...");
    }
}

class Dog {
    public void wang() {
        System.out.println("Dog wang...");
    }
}

public class Main {

    public static void main(String[] args) {
        Child child = new Child();
        //do sh
        child.wakeUp();
    }
}
