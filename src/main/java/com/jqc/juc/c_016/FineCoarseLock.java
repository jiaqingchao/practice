/**
 * synchronized优化
 * 同步代码块中的语句越少越好
 * 比如m1和m2
 * m2是m1锁的细化
 * <p>
 * 当一个业务逻辑有许多比较细碎的锁，那么直接给这个业务逻辑加锁，
 * 可以降低锁的争用，这是锁的粗化
 */
package com.jqc.juc.c_016;

import java.util.concurrent.TimeUnit;

public class FineCoarseLock {
    int count = 0;

    synchronized void m1() {
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //业务逻辑中只有下面这句需要sync,这是不应该给整改方法上锁
        count++;

        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2() {
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中只有下面这句需要sync,这是不应该给整改方法上锁
        //采用细粒度的锁，可以使线程争用时间变短，从而提高效率
        synchronized (this) {
            count++;
        }
        //do sth need not sync
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
