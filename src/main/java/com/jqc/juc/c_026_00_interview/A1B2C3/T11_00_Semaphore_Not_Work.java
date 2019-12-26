package com.jqc.juc.c_026_00_interview.A1B2C3;

import java.util.concurrent.Semaphore;

public class T11_00_Semaphore_Not_Work {
    static Thread t1= null,t2=null;

    static Semaphore s = new Semaphore(1);

    public static void main(String[] args) {
        t1 = new Thread(()->{
            for (int c: CONSTANTS.numbers) {
                try {
                    s.acquire();
//                    LockSupport.park();
                    System.out.print(c);
//                    LockSupport.unpark(t2);
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2 = new Thread(()->{
            for (char c: CONSTANTS.letters) {
                System.out.print(c);
//                LockSupport.unpark(t1);
//                LockSupport.park();
                try {
                    s.release();
                    s.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
