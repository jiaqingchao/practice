
/**
 * 有N张火车票每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题
 * 重复销售？超量销售
 */
package com.jqc.juc.c_024_FromVectorToQueue;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();
    static {
        int ticketNum = new Random().nextInt(900) + 100;
        for (int i = 0;i < ticketNum; i++) {
            tickets.add("票编号：" + i);
        }
    }
    public static void main(String[] args) {
        for(int i = 0; i< 10; i++){
            new Thread(()->{
                while (true){
                    String s = tickets.poll();
                    if(s == null){
                        break;
                    }
                    System.out.println("销售了 : " + s);
                }
            }).start();
        }

    }
}

