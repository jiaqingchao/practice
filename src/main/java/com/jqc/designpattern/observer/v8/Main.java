package com.jqc.designpattern.observer.v8;

import java.util.ArrayList;
import java.util.List;

/**
 * 有很多时候，观察者需要根据事件的具体情况来进行处理
 * 大多时候，我们处理事件的时候，需要事件源对象
 * 事件也可以形成继承体系
 */

class Child {
    private boolean cry = false;
    private List<Observer> observers = new ArrayList<>();

    {
        observers.add(new Dad());
        observers.add(new Mum());
        observers.add(new Dog());
        observers.add((e) -> {
            System.out.println("ppp");
        });
        //hook callback function
    }

    public boolean isCry() {
        return cry;
    }

    wakeUpEvent event = new wakeUpEvent(System.currentTimeMillis(), "bed", this);

    public void wakeUp() {
        cry = true;
        for (Observer observer : observers) {
            observer.actionOnWakeup(event);
        }
    }
}

abstract class Event<T> {
    abstract T getSource();
}

class wakeUpEvent extends Event<Child> {
    long timestamp;
    String loc;
    Child source;

    public wakeUpEvent(long timestamp, String loc, Child source) {
        this.timestamp = timestamp;
        this.loc = loc;
        this.source = source;
    }

    @Override
    Child getSource() {
        return source;
    }
}

interface Observer {
    void actionOnWakeup(wakeUpEvent event);
}

class Dad implements Observer {
    public void feed() {
        System.out.println("Dad feeding...");
    }

    @Override
    public void actionOnWakeup(wakeUpEvent event) {
        feed();
    }
}

class Mum implements Observer {
    public void hug() {
        System.out.println("Mum hugging...");
    }

    @Override
    public void actionOnWakeup(wakeUpEvent event) {
        hug();
    }
}

class Dog implements Observer {
    public void wang() {
        System.out.println("Dog wang...");
    }

    @Override
    public void actionOnWakeup(wakeUpEvent event) {
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
