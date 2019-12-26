package com.jqc.juc.c_026_00_interview.A1B2C3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class T13_00_TransferQueue {
    static Thread t1= null,t2=null;

    static TransferQueue<String> transferQueue = new LinkedTransferQueue<>();

    public static void main(String[] args) {
        t1 = new Thread(()->{
            for (int c: CONSTANTS.numbers) {
                try {
                    System.out.print(transferQueue.take());
                    transferQueue.transfer(String.valueOf(c));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2 = new Thread(()->{
            for (char c: CONSTANTS.letters) {
                try {
                    transferQueue.transfer(String.valueOf(c));
                    System.out.print(transferQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
