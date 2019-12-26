package com.jqc.juc.c_025;

import java.util.concurrent.LinkedTransferQueue;

public class T09_TransferQueue {

    public static void main(String[] args) {//容量为0
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        try {
            strs.transfer("aaa");////阻塞等待消费者消费
//            strs.transfer("bbb");
//            strs.put("aaa");
//            strs.put("bbb");
//            strs.add("aaa");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        new Thread(()->{
//            try {
//                System.out.println(strs.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
        System.out.println(strs.size());
    }
}
