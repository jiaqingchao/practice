package com.jqc.juc.c_004;

/**
 * synchronized关键字
 * 对某个对象锁
 *
 * @author jqingchao
 */
public class T {
    private static int count = 10;

    public synchronized static void m() {//这里等同于synchronized(T.class)
        count--;
        System.out.println(Thread.currentThread().getName() + ",count : " + count);
    }

    public static void mm() {//考虑一下这里写sychronized(this)是否可以
        //this只能获取到当前的具体实现，不能通过访问class，所以不能使用sychronized(this)

        //synchronized (this){ // 有异常
        synchronized (T.class) {
            count--;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            T.mm();
            System.out.println(T.count);
        }
    }
}
