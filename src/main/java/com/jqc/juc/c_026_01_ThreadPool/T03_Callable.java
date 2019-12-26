package com.jqc.juc.c_026_01_ThreadPool;

import java.util.concurrent.*;

public class T03_Callable {
    public static void main(String[] args) {
        Callable<String> e = ()-> "Hello Callable";
        ExecutorService service = Executors.newCachedThreadPool();

        Future<String> future = service.submit(e);

        try {
            System.out.println(future.get());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }

        service.shutdown();

    }
}
