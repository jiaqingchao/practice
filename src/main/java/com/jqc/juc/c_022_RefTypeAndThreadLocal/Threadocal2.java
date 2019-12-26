package com.jqc.juc.c_022_RefTypeAndThreadLocal;

import java.util.concurrent.TimeUnit;

public class Threadocal2 {
    //volatile static Person p = new Person();
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
//        tl.set(new Person()); //主线程set,其他线程访问不到
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //tl.get().name = "jiaqingchao";//主线程添加，访问不到
            tl.set(new Person());
        }).start();
    }

    static class Person {
        String name = "jqc";
        ;
    }
}
