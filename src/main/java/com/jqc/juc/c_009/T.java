package com.jqc.juc.c_009;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，
 * 一个线程已经拥有某个对象的锁，
 * 再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized获得的锁时可以重入的
 *
 * @author jqingchao
 */
public class T {
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println(Thread.currentThread().getName() + " m1 send");
    }

    synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + " m2 start...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 send");
    }

    public static void main(String[] args) {
        T t = new T();
        t.m1();

//       new Thread(t::m1,"t1").start();
//       new Thread(t::m2,"t2").start();
    }
}
