package com.jqc.juc.c_026_00_interview.A1B2C3;

public class T03_00_sync_notify {
    static Thread t1= null,t2=null;
    public static void main(String[] args) {
        final Object lock = new Object();

        t1 = new Thread(()->{
            synchronized (lock){
                for (int c: CONSTANTS.numbers) {
                    try {
                        lock.wait();
                        System.out.print(c);
                        lock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2 = new Thread(()->{
            synchronized (lock){
                for (char c: CONSTANTS.letters) {
                    try {
                        System.out.print(c);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
