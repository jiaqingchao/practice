package com.jqc.juc.c_025;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> lists =
//                new ArrayList<>();//这个会出并发问题
                new Vector();
                new CopyOnWriteArrayList<>();

        Random r = new Random();
        Thread[] threads = new Thread[100];
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    lists.add("a"+r.nextInt(10_0000));
                }
            });
        }

        runAndCompueTime(threads);

        System.out.println(lists.size());
    }

    private static void runAndCompueTime(Thread[] threads) {
        long start = System.currentTimeMillis();
        Arrays.asList(threads).forEach(t->t.start());
        Arrays.asList(threads).forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
