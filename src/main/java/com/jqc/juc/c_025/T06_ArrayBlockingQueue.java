package com.jqc.juc.c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i< 10; i++){
            strs.put("a"+i);

        }
//        strs.put("aaa");//满了就会等待
//        strs.add("aaa"); // 满了会抛异常
//        strs.offer("aaa");//满了没有加进去返回false
        strs.offer("aaa",1, TimeUnit.SECONDS); //没满直接加，满了，在一秒之后尝试加，没有加进去返回false，

        System.out.println(strs);
    }
}
