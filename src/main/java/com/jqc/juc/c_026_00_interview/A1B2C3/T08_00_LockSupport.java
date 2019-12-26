package com.jqc.juc.c_026_00_interview.A1B2C3;

import java.util.concurrent.locks.LockSupport;

public class T08_00_LockSupport {
    static Thread t1= null,t2=null;

    public static void main(String[] args) {
        t1 = new Thread(()->{
            for (int c: CONSTANTS.numbers) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t2);
            }
        });
        t2 = new Thread(()->{
            for (char c: CONSTANTS.letters) {
                System.out.print(c);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        });
        t1.start();
        t2.start();
    }
}
