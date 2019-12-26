package com.jqc.juc.c_026_01_ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class T09_FixedThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        getPrime(1,20_0000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);


        final int cpuCoreNum = 4;
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

        MyTask t1 = new MyTask(1, 8_0000);
        MyTask t2 = new MyTask(8_0001, 13_0000);
        MyTask t3 = new MyTask(13_0001, 17_0000);
        MyTask t4 = new MyTask(17_0001, 20_0000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);
        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);

        service.shutdown();
    }

    static class MyTask implements Callable<List<Integer>> {
        int start;
        int end;

        public MyTask(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public List<Integer> call(){
            return getPrime(start, end);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static boolean isPrime(int num){
        for(int i = 2; i<=num/2;i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
    public static List<Integer> getPrime(int start, int end){
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if(isPrime(i)){
                list.add(Integer.valueOf(i));
            }
        }
        return list;
    }
}
