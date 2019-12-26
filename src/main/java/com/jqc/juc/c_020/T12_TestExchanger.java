package com.jqc.juc.c_020;

import java.util.concurrent.Exchanger;

public class T12_TestExchanger {
    static Exchanger<String> exchanger = new Exchanger<>(); //交换，两两交换

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);//阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "T1").start();
        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);//阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "T2").start();
        new Thread(() -> {
            String s = "T3";
            try {
                s = exchanger.exchange(s);//阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "T3").start();
    }
}
