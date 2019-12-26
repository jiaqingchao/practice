/**
 * 在下面的代码中，runing 是存在于堆内存中的对象中
 * 当线程t1开始运行的时候，会runing 的值从内存中读到线程t1的工作区，在运行过程中直接使用copy,
 * 并不会每次都去读取堆内存，这样，当主线程修改running的值后，t1线程感知不到，所以不会停止运行
 * <p>
 * 使用volatile,将会强制所用线程都去堆内存中读取running的值
 * <p>
 * 可以阅读这篇文章进行更深入的理解
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 * <p>
 * volatile并不能保证多个线程共同修改running变量所带来的的不一致问题，也就是说volatile 不能替代 synchronized
 *
 * @authhor jiaqingchao
 */
package com.jqc.juc.c_012;

import java.util.concurrent.TimeUnit;

public class T {
    /*volatile*/ boolean running = true; // 对比一下有无volatile的情况下，整个程序运行的区别

    void m() {
        System.out.println("m start");
        while (running) {
//            try { // 加了睡眠会重新从堆内存中获取running,也就获取改变了改变的值，
//            测试volatile不要加sleep
//                TimeUnit.MICROSECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        T t = new T();
        //new Thread(()->t.m(), "t1").start();
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}


