package com.jqc.juc.c_026_00_interview.A1B2C3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T09_00_BlockingQueue {
    static Thread t1= null,t2=null;

    static BlockingQueue<String> str = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> str2 = new ArrayBlockingQueue<>(1);
    public static void main(String[] args){
        t1 = new Thread(()->{
            for (int c: CONSTANTS.numbers) {
                try {
//                    System.out.print(str2.take());
//                    str.put(String.valueOf(c));
                    str.take();
                    System.out.print(c);
                    str2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2 = new Thread(()->{
            for (char c: CONSTANTS.letters) {
                try {
//                    str2.put(String.valueOf(c));
//                    System.out.print(str.take());

                    System.out.print(c);
                    str.put("ok");
                    str2.take();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
