package com.jqc.designpattern.spring.v2;

import java.util.Random;

public class Tank {
    public void move(){
        System.out.println("Tank moving claclacal...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
