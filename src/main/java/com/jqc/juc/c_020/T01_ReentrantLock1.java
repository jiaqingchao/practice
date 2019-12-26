/**
 * reentrantlock用于替代synchronized
 * 本例中由于m1锁定this,只有m1执行完毕的时候，m2才能执行
 * 这里复习synchronized最原始的语义
 *
 * @author jiaqingchao
 */
package com.jqc.juc.c_020;

import java.util.concurrent.TimeUnit;

public class T01_ReentrantLock1 {
    synchronized void m1() {
        try {
            for (int i = 0; i < 10; i++) {

                TimeUnit.SECONDS.sleep(1);

                System.out.println(i);
                if (i == 2) {//重入
                    m2();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void m2() {
        System.out.println("m2 start...");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 rl = new T01_ReentrantLock1();
        new Thread(rl::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // new Thread(rl::m2).start();
    }
}
