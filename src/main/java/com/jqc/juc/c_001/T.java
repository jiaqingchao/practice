package com.jqc.juc.c_001;

/**
 * synchronized关键字
 * 对某个对象锁
 *
 * @author jqingchao
 */
public class T {
    private int count = 10;
    private Object o = new Object();

    public void m() {
        synchronized (o) {//任何线程要执行下边的代码，必须先拿到o的锁
            count--;
            System.out.println(Thread.currentThread().getName() + ",count : " + count);
        }
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 10; i++) {
            t.m();
        }
    }
}
