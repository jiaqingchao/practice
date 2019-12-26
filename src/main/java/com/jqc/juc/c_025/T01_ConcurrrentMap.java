package com.jqc.juc.c_025;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrrentMap {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
//        Map<String, String> map = new ConcurrentSkipListMap<>();

//        Map<String, String> map = new Hashtable<>();
//        Map<String, String> map = new HashMap<>();
//        TreeMap
        Random r = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        long start = System.currentTimeMillis();
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(()->{
                map.put("a"+r.nextInt(10_0000),"a"+10_0000);
                latch.countDown();
            });
        }
        Arrays.asList(threads).forEach(t->t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(map.size());

    }
}
