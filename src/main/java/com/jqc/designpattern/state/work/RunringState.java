package com.jqc.designpattern.state.work;

public class RunringState extends CarState_ {
    Car c;

    public RunringState(Car c) {
        this.c = c;
    }

    @Override
    void open() {
        System.out.println("runing not to opened");
    }

    @Override
    void close() {
        System.out.println("runing not to closed");
    }

    @Override
    void run() {
        System.out.println("already is runing");
    }

    @Override
    void stop() {
        c.state = new StopedState(c);
        System.out.println("runing to stoped");
    }
}
