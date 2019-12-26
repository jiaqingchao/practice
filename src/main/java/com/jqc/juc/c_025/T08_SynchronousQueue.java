package com.jqc.juc.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T08_SynchronousQueue {

    public static void main(String[] args) {//容量为0
        BlockingQueue<String> strs = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            strs.put("aaa");//阻塞等待消费者消费
//            strs.put("bbb");
            strs.add("aaa"); //容量为0.不能add
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(strs.size());
    }
}
