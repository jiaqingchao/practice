package com.jqc.designpattern.observer.v5;

import java.util.ArrayList;
import java.util.List;

class Child {
    private boolean cry = false;
    private List<Observer> observers = new ArrayList<>();

    {
        observers.add(new Dad());
        observers.add(new Mum());
        observers.add(new Dog());
    }

    public boolean isCry() {
        return cry;
    }

    public void wakeUp() {
        cry = true;
        for (Observer observer : observers) {
            observer.actionOnWakeup();
        }
    }
}

interface Observer {
    void actionOnWakeup();
}

class Dad implements Observer {
    public void feed() {
        System.out.println("Dad feeding...");
    }

    @Override
    public void actionOnWakeup() {
        feed();
    }
}

class Mum implements Observer {
    public void hug() {
        System.out.println("Mum hugging...");
    }

    @Override
    public void actionOnWakeup() {
        hug();
    }
}

class Dog implements Observer {
    public void wang() {
        System.out.println("Dog wang...");
    }

    @Override
    public void actionOnWakeup() {
        wang();
    }
}

public class Main {

    public static void main(String[] args) {
        Child child = new Child();
        //do sh
        child.wakeUp();
    }
}
