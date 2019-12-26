package com.jqc.juc.c_021_01_interview;

import java.util.LinkedList;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 使用wait和notify/notifyAll来实现
 *
 * @param <T>
 */
public class MyContainer1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while (lists.size() == MAX) { // 想想为什么用while而不是用if
            try {
                this.wait(); // effective java  //用if之后，等在这在开始运行后就不会再去判断size了，而是直接添加，可能会造成呢个列表长度超过最大数量
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        this.notifyAll(); // 通知消费者进行消费 //同时也会叫醒其他生产者
    }

    public int getCount() {
        return count;
    }

    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        --count;
        this.notifyAll();//通知生产者进行生产//同时也会叫醒其他消费者
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> list = new MyContainer1<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(list.get());
                }

            }, "readThread" + i).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    list.put(Thread.currentThread().getName() + " " + j);
                }

            }, "writeThread" + i).start();
        }

    }
}
