
/**
 * 有N张火车票每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题
 * 重复销售？超量销售
 */
package com.jqc.juc.c_024_FromVectorToQueue;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketSeller_my {

    static int ticketNum;
    static AtomicInteger sellCount;
    static Queue<TrainTicket> trainTickets;
    static {
        sellCount = new AtomicInteger();
        ticketNum = new Random().nextInt(9900) + 100;
        trainTickets = new ConcurrentLinkedQueue<>();
        for (int i = 0;i < ticketNum; i++) {
            trainTickets.add(new TrainTicket());
        }
    }


    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for(int i = 0; i< 10; i++){
            threads[i] = new Thread(()->{
                 while (true){
                     TrainTicket trainTicket = trainTickets.poll();
                     if(trainTicket == null){
                         break;
                     }
                     sellCount.getAndIncrement();
                }
            });
        }
        for (int i = 0; i< 10; i++){
            threads[i].start();
        }
        for (int i = 0; i< 10; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ticketNum);
        System.out.println(sellCount);

    }
    static class TrainTicket{
        UUID id = UUID.randomUUID();
    }
}

