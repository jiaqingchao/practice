package com.jqc.juc.c_026_00_interview.A1B2C3;

import java.util.concurrent.atomic.AtomicInteger;

public class T07_00_AtomicInteger {
    static Thread t1= null,t2=null;
    static AtomicInteger threadNo = new AtomicInteger(1);
    public static void main(String[] args) {
        t1 = new Thread(()->{
            for (int c: CONSTANTS.numbers) {
                while(threadNo.get() != 1) {}
                System.out.print(c);
                threadNo.set(2);
            }
        });
        t2 = new Thread(()->{
            for (char c: CONSTANTS.letters) {
                while(threadNo.get() != 2) {}
                System.out.print(c);
                threadNo.set(1);
            }
        });
        t1.start();
        t2.start();
    }
}
