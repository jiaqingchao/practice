package com.jqc.juc.c_026_00_interview.A1B2C3;

import java.util.concurrent.Exchanger;

public class T12_00_Exchanger_Not_Work { // 交换完成后两个线程会同时会执行，会导致一个线程连续输出两次
    static Thread t1= null,t2=null;

    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args){
        t1 = new Thread(()->{
            for (int c: CONSTANTS.numbers) {
                System.out.print(c);
                try {
                    exchanger.exchange("T2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t2 = new Thread(()->{
            for (char c: CONSTANTS.letters) {

                try {
                    exchanger.exchange("T1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(c);
            }
        });
        t1.start();
        t2.start();
    }
}
