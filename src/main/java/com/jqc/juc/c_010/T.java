package com.jqc.juc.c_010;

/**
 * 一个同步方法可以调用另外一个同步方法，
 * 一个线程已经拥有某个对象的锁，
 * 再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized获得的锁时可以重入的
 *
 * 这里继承有可能发生的情形，子类调用父类的同步方法
 * @author jqingchao
 */
public class T extends TT{
    @Override
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        super.m1();

        System.out.println(Thread.currentThread().getName()+ " m1 send");
   }

    public static void main(String[] args) {
        T t = new T();
        t.m1();

//       new Thread(t::m1,"t1").start();
//       new Thread(t::m2,"t2").start();
    }
}
class TT{
    protected synchronized void m1(){
        System.out.println("T_P m1");
    }
}
