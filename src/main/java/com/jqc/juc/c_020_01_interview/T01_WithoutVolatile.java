/**
 * 曾经的面试题（淘宝？）
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5时，线程2给出提示并结束
 **/
package com.jqc.juc.c_020_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T01_WithoutVolatile {
    List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T01_WithoutVolatile wv = new T01_WithoutVolatile();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                wv.add(new Object());
                System.out.println("add" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (wv.size() == 5) { //线程不可见
                    System.out.println("已添加5个元素");
                    break;
                }
            }
            System.out.println("t2 end");
        }, "t2").start();
    }

}
