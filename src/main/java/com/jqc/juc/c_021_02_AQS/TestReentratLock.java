package com.jqc.juc.c_021_02_AQS;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentratLock {

    private static volatile int i = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
//        synchronized (TestReentratLock.class){
        i++;
//        }
        lock.unlock();

        //synchronized 程序员的丽春院 JUC
    }

}
