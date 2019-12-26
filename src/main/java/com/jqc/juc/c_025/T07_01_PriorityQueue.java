package com.jqc.juc.c_025;

import java.util.PriorityQueue;

public class T07_01_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue();

        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");
        while (q.size()> 0) {
            System.out.println(q.poll());
        }

    }
}
