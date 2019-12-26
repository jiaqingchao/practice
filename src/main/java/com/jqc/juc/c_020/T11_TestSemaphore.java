package com.jqc.juc.c_020;

import java.util.concurrent.Semaphore;

public class T11_TestSemaphore {
    public static void main(String[] args) {
        //Semaphore s = new Semaphore(2);
        Semaphore s = new Semaphore(2, true);//fair是否公平
        //允许一个线程同时执行

        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T1.runing...");
                Thread.sleep(200);
                System.out.println("T1.runing...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T2.runing...");
                Thread.sleep(200);
                System.out.println("T2.runing...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();
    }
}
