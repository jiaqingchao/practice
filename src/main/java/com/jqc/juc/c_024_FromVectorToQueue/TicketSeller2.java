
/**
 * 有N张火车票每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题
 * 重复销售？超量销售
 */
package com.jqc.juc.c_024_FromVectorToQueue;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();
    static {
        int ticketNum = new Random().nextInt(900) + 100;
        for (int i = 0;i < ticketNum; i++) {
            tickets.add("票编号：" + i);
        }
    }
    public static void main(String[] args) {
        for(int i = 0; i< 10; i++){
            new Thread(()->{
                while (tickets.size() > 0){//size方法虽然加锁，但size == 1时 因为下边业务代码导致其他线程也进入循环了
                    //达到买票的要求要保证整个循环内部的原子性
                    try {//业务
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了 : " + tickets.remove(0));
                }
            }).start();
        }

    }
}

