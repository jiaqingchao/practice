package com.jqc.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.concurrent.*;

public class T05_00_HelloThreadPool {
    static class Task implements Runnable{
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task "+ i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,5,60,TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            pool.execute(new Task(i));
        }
        System.out.println(pool.getQueue());

        pool.execute(new Task(100));
        System.out.println(pool.getQueue());

        pool.shutdown();
    }
}
