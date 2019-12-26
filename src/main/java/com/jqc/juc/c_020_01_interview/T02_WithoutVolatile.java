/**
 * 曾经的面试题（淘宝？）
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5时，线程2给出提示并结束
 * <p>
 * 线程之间是不可见了，加了volatile线程可见，但因是引用对象，其引用并没有发生改变，所以其还是线程不可见的，
 * 还是有问题，就算是加了同步，也不能保证其线程可见
 **/

package com.jqc.juc.c_020_01_interview;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//volatile 除了面试，没有把握就不要用
//volatile 不要用于修饰引用对象
public class T02_WithoutVolatile {
    //添加volatile,使t2能够得到通知
    //volatile List lists = new ArrayList(); // 使用同步容器
    // 保证可见，保证同步,

    List lists = Collections.synchronizedList(new LinkedList<>());

    public void add(Object o) {
        lists.add(o);
    } //添加对象和更新size要是同步的

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T02_WithoutVolatile wv = new T02_WithoutVolatile();
        new Thread(() -> {
            while (true) {
                if (wv.size() == 5) { //线程不可见
                    System.out.println("已添加5个元素");
                    break;
                }
            }
            System.out.println("t2 end");
        }, "t2").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                wv.add(new Object());
                System.out.println("add" + i);
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }, "t1").start();


    }

}
