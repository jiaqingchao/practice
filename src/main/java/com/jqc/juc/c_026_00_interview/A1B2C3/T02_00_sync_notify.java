package com.jqc.juc.c_026_00_interview.A1B2C3;

public class T02_00_sync_notify {
    private static volatile boolean t2Started = false;
    static Thread t1= null,t2=null;
    public static void main(String[] args) {
        final Object lock = new Object();

        t1 = new Thread(()->{

            synchronized (lock){
                while (!t2Started){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int c: CONSTANTS.numbers) {
                    try {
                        System.out.print(c);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });
        t2 = new Thread(()->{
            synchronized (lock){
                t2Started = true;
                for (char c: CONSTANTS.letters) {
                    try {
                        System.out.print(c);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });
        t1.start();
        t2.start();
    }
}
