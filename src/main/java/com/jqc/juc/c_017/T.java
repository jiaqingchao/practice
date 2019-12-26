/**
 * 锁定某个对象o,如果o的属性发送改变，不影响锁的使用
 * 但如果o变成另外一个对象，则锁定的对象发生改变
 * 应该避免将锁定的对象的引用改变为另外的对象
 */
package com.jqc.juc.c_017;

import java.util.concurrent.TimeUnit;

public class T {
    /*final*/ Object o = new Object();//o不能发生改变，所以要加final

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(t::m, "t2");
        t.o = new Object(); //锁对象发生改变,所以t2得以执行，如果注释掉这行t2将永远得不到执行
        t2.start();
    }
}
