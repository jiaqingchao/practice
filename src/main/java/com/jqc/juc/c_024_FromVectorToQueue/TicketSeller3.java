
/**
 * 有N张火车票每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题
 * 重复销售？超量销售
 */
package com.jqc.juc.c_024_FromVectorToQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TicketSeller3 {
    static List<String> tickets = new LinkedList<>();
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
                    synchronized (tickets){
                        if(tickets.size() <= 0){
                            break;
                        }
                        System.out.println("销售了 : " + tickets.remove(0));
                    }

                }
            }).start();
        }

    }
}

