package com.jqc.juc.c_026_00_interview.A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T04_00_lock_condition {
    static Thread t1= null,t2=null;
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        t1 = new Thread(()->{
            try {
                lock.lock();
                for (int c: CONSTANTS.numbers) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            });
        t2 = new Thread(()->{
            try {
                lock.lock();
                for (char c: CONSTANTS.letters) {
                    System.out.print(c);
                        condition.signal();
                        condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        t1.start();
        t2.start();

    }
}
