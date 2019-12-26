package com.jqc.juc.c_026_01_ThreadPool;

import java.util.concurrent.*;

public class T14_MyRejectedHandle {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4, 4,
                0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(6),
                new MyHandler()
        );
    }
    static class  MyHandler implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //log("r rejected")
            //sava r kafka/mysql/redis
            //try 3 times
            if(executor.getQueue().size() < 10000){
                //try put again
            }
            ;
        }
    }
}

