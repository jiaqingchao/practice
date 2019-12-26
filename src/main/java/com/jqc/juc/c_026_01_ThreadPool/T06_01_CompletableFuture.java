/**
 *  假设你能提供一个服务
 *  这个服务查现场各大电商同一类产品的价格并汇总展示
 *
 */
package com.jqc.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class T06_01_CompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        long start, end;

//        start = System.currentTimeMillis();
//        priceOfTM();
//        priceOfTB();
//        priceOfJD();
//        end = System.currentTimeMillis();
//        System.out.println("uer serial method call! " + (end - start));

        start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(()->priceOfTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(()->priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(()->priceOfJD());

//        CompletableFuture.supplyAsync(() -> priceOfTM())
//                .thenApply(String::valueOf)
//                .thenApply(str -> "price " + str)
//                .thenAccept(System.out::println);

        CompletableFuture.allOf(futureTM,futureTB,futureJD).join();

        end = System.currentTimeMillis();
        System.out.println("uer completable future! " + (end - start));

    }



    private static double priceOfTM() {
        delay();
        return 1.00;
    }
    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("After "+ time +" sleep!");
    }
}
