package com.jqc.designpattern.allDP;

public class State {
    public static void main(String[] args) {
        Car2 c = new Car2();
        c.setState(new ClosedState(c));
        c.run();
        c.stop();
        c.run();
        c.stop();
        c.open();
        c.close();
        c.open();
    }
}

class Car2 {
    CarState state;

    public Car2() {
    }

    public void setState(CarState state) {
        this.state = state;
    }

    public void open() {
        state.open();
    }

    public void close() {
        state.close();
    }

    public void run() {
        state.run();
    }

    public void stop() {
        state.stop();
    }
}

abstract class CarState {
    public abstract void open();

    public abstract void close();

    public abstract void run();

    public abstract void stop();
}

class ClosedState extends CarState {
    Car2 c;

    public ClosedState(Car2 c) {
        this.c = c;
    }

    @Override
    public void open() {
        c.setState(new OpenedState(c));
        System.out.println("closed to opened");
    }

    @Override
    public void close() {
        System.out.println("is closed");
    }

    @Override
    public void run() {
        c.setState(new RuningState(c));
        System.out.println("closed to runing");
    }

    @Override
    public void stop() {
        System.out.println("is closed");
    }
}

class OpenedState extends CarState {
    Car2 c;

    public OpenedState(Car2 c) {
        this.c = c;
    }

    @Override
    public void open() {
        System.out.println("is opened");
    }

    @Override
    public void close() {
        c.setState(new ClosedState(c));
        System.out.println("opened to closed");
    }

    @Override
    public void run() {
        System.out.println("is opened");
    }

    @Override
    public void stop() {
        System.out.println("is opened");
    }
}

class RuningState extends CarState {
    Car2 c;

    public RuningState(Car2 c) {
        this.c = c;
    }

    @Override
    public void open() {
        System.out.println("is runing");
    }

    @Override
    public void close() {
        System.out.println("is runing");
    }

    @Override
    public void run() {
        System.out.println("is runing");
    }

    @Override
    public void stop() {
        c.setState(new StopedState(c));
        System.out.println("runing to stoped");
    }
}

class StopedState extends CarState {
    Car2 c;

    public StopedState(Car2 c) {
        this.c = c;
    }

    @Override
    public void open() {
        c.setState(new OpenedState(c));
        System.out.println("stoped to opened");
    }

    @Override
    public void close() {
        System.out.println("is stoped");
    }

    @Override
    public void run() {
        c.setState(new RuningState(c));
        System.out.println("stoped to runing");
    }

    @Override
    public void stop() {
        System.out.println("is stoped");
    }
}