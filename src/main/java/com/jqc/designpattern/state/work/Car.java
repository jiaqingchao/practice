package com.jqc.designpattern.state.work;

public class Car {
    CarState_ state;
    void open(){
        state.open();
    }
    void colse(){state.close();}
    void run(){state.run();}
    void stop(){state.stop();}


    public static void main(String[] args) {
        Car c = new Car();
        c.state = new ClosedState(c);
        c.colse();
        c.stop();
        c.run();

        c.run();
        c.open();
        c.colse();
        c.stop();

        c.stop();
        c.colse();
        c.open();

        c.open();
        c.run();
        c.stop();
        c.colse();


    }
}
