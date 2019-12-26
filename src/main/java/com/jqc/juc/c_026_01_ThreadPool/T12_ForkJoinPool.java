package com.jqc.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class T12_ForkJoinPool {
    static int[] nums = new int[100_0000];
    static final int MAX_NUM = 5_0000;
    static Random r = new Random();
    static {
        for (int i = 0; i <nums.length ; i++) {
            nums[i] = r.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum());
    }
    static class AddTask extends RecursiveAction{
        int start,end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if(end - start <= MAX_NUM){
                long sum = 0L;
                for (int i = start; i <end ; i++) {
                    sum += nums[i];
                }
                System.out.println("from:" + start + "to:" + end + " = " + sum);
            }else {
                int middle = start + (end - start)/2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }

    static class AddTaskRet extends RecursiveTask<Long> {
        int start,end;

        public AddTaskRet(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if(end - start <= MAX_NUM){
                long sum = 0L;
                for (int i = start; i <end ; i++) {
                    sum += nums[i];
                }
                return sum;
            }
            int middle = start + (end - start)/2;
            AddTaskRet subTask1 = new AddTaskRet(start, middle);
            AddTaskRet subTask2 = new AddTaskRet(middle, end);
            subTask1.fork();
            subTask2.fork();

            return subTask1.join() + subTask2.join();

        }
    }

    public static void main(String[] args) throws IOException {
//        ForkJoinPool fjp = new ForkJoinPool();
//        AddTask subTask = new AddTask(0, nums.length);
//        fjp.execute(subTask);


        ForkJoinPool fjp = new ForkJoinPool();
        AddTaskRet subTaskRet = new AddTaskRet(0, nums.length);
        fjp.execute(subTaskRet);
        System.out.println(subTaskRet.join());

        //System.in.read();

    }
}
