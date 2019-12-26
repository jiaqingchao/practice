package com.jqc.juc.c_018;

public class T {
    String s1 = "Hello";
    String s2 = "Hello";

    void m1() {
        synchronized (s1) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    void m2() {
        synchronized (s2) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
    }
}
