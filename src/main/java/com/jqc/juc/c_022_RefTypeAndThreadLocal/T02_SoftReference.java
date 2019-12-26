package com.jqc.juc.c_022_RefTypeAndThreadLocal;

import java.lang.ref.SoftReference;

public class T02_SoftReference {
    public static void main(String[] args) {
        SoftReference<byte[]> sf = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(sf.get());
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(sf.get());

        //再分配一个数组，heap将装不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] b = new byte[1024 * 1024 * 15]; // ava.lang.OutOfMemoryError: Java heap space
        System.out.println(sf.get());

    }
}
