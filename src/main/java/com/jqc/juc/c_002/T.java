package com.jqc.juc.c_002;

/**
 * synchronized关键字
 * 对某个对象锁
 *
 * @author jqingchao
 */
public class T {
    private int count = 10;

    public void m() {
        synchronized (this) {//任何线程要执行下边的代码，必须先拿到this的锁
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
