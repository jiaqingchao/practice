package com.jqc.designpattern.state.work;

public class ClosedState extends CarState_ {
    Car c;

    public ClosedState(Car c) {
        this.c = c;
    }

    public ClosedState() {
    }

    @Override
    void open() {
        c.state = new OpenedState(c);
        System.out.println("closed to opened");
    }

    @Override
    void close() {
        System.out.println("already is close");
    }

    @Override
    void run() {
        c.state = new RunringState(c);
        System.out.println("closed to runing");
    }

    @Override
    void stop() {
        System.out.println("closed not to stoped");
    }
}
