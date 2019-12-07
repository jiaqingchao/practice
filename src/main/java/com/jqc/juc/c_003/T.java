package com.jqc.juc.c_003;

/**
 * synchronized关键字
 * 对某个对象锁
 * @author jqingchao
 */
public class T {
    private int count = 10;
    public synchronized void m(){//等同于在方法的代码执行时synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName()+ ",count : "+ count);
    }

    public static void main(String[] args) {
        T t = new T();
        for(int i=0;i<10;i++){
            t.m();
        }
    }
}
