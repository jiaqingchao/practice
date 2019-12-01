package com.jqc.designpattern.state.work;

public class OpenedState extends CarState_ {
    Car c;

    public OpenedState(Car c) {
        this.c = c;
    }

    @Override
    void open() {
        System.out.println("already is opened");
    }

    @Override
    void close() {
        c.state = new ClosedState(c);
        System.out.println("opened to closed");
    }

    @Override
    void run() {
        System.out.println("opened not to runing");
    }

    @Override
    void stop() {
        System.out.println("opened not to stoped");
    }
}
