/**
 * 曾经的面试题（淘宝？）
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5时，线程2给出提示并结束
 * <p>
 * 给lists添加volatile之后，t2能够接收到通知，但是t2线程的死循环很浪费cpu,如果不用死循环该怎么做
 * <p>
 * 这里使用wait和notify做到，wait会释放锁，而natify不会释放锁
 * 需要注意，用这种方法，要保证线程2先执行，也就是首先让t2监听才可以
 * <p>
 * 阅读下面的程序，并分析结果
 * 可以读到输出结果并并不是size==5时t2退出，而是t1结束时t2才接收到通知而退出
 * 想想这是为什么
 * <p>
 * notify 之后，t1必须释放锁，t2退出后，也必须notify,通知t1继续执行
 * 整个通信过程比较频繁
 */

package com.jqc.juc.c_020_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//volatile 除了面试，没有把握就不要用
//volatile 不要用于修饰引用对象
public class T04_NotifyFreeLock {

    //volatile List lists = Collections.synchronizedList(new LinkedList<>());
    List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T04_NotifyFreeLock nfl = new T04_NotifyFreeLock();
        final Object lock = new Object();


        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2启动");
                if (nfl.size() != 5) { //线程不可见
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
                //通知t1继续执行
                lock.notify();
            }

        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    nfl.add(new Object());
                    System.out.println("add" + i);
                    if (nfl.size() == 5) {
                        lock.notify();//不释放锁

                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }, "t1").start();

    }

}