package com.jqc.designpattern.state.work;

public class StopedState extends CarState_ {
    Car c;

    public StopedState(Car c) {
        this.c = c;
    }

    @Override
    void open() {
        c.state = new OpenedState(c);
        System.out.println("stoped to opened");
    }

    @Override
    void close() {
        System.out.println("stoped not to closed");
    }

    @Override
    void run() {
        c.state = new RunringState(c);
        System.out.println("runing to opened");
    }

    @Override
    void stop() {
        System.out.println("already is stoped");
    }
}
