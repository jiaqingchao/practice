/**
 * 程序执行过程中，如果出现异常，默认情况所会被释放
 * 所以，在并发处理的过程中，有异常要多加小心，不然会发生不一致的情况
 * 比如，在wb app 处理过程中，多个sevlet线程共同范围一个资源，者时如果异常处理不合理
 * 在第一个线程中抛出异常，其他线程就会进入同步代码器，有可能会访问到异常产生的数据
 * 因此要非常小心的处理同步业务逻辑中的异常
 */
package com.jqc.juc.c_011;

import java.util.concurrent.TimeUnit;

public class T {
    int count = 0;

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                int i = 1 / 0;//此处抛出异常，锁将被释放，要想不释放，可以在这里catch,然后让循环继续
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };
        new Thread(r, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r, "t2").start();
    }
}
