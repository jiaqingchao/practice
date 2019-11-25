package com.jqc.designpattern.observer.v3;

class Child {
    private boolean cry = false;
    private Dad d = new Dad();
    public boolean isCry() {
        return cry;
    }

    public void wakeUp(){
        cry = true;
        d.feed();
    }
}
class Dad{
    public void feed(){
        System.out.println("Dad feeding...");
    }
}

public class Main {

    public static void main(String[] args) {
        Child child = new Child();
        //do sh
        child.wakeUp();
    }
}
