package com.jqc.juc.c_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_ConcurentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();
        for(int i=0;i<10;i++){
            strs.offer("a"+i);//add，返回boolean
        }

        System.out.println(strs);

        System.out.println(strs.size());

        System.out.println(strs.poll());//取第一个，并去掉
        System.out.println(strs.size());

        System.out.println(strs.peek());//取第一个
        System.out.println(strs.size());
    }
}
