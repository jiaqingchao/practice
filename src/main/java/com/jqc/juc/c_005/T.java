package com.jqc.juc.c_005;

/**
 * 分析一下这个程序的输出
 * @author jqingchao
 */
public class T implements Runnable{
    private /*volatile*/ int count = 100;
    public /*synchronized*/ void run(){//这里等同于synchronized(T.class)
        count--;
        //--后的同时，其他线程的--在输出之前，则输出的数值与应该输出的值不一致
        System.out.println(Thread.currentThread().getName()+ ",count : "+ count);
    }

    public static void main(String[] args) {
        T t = new T();
        for(int i=0;i<100;i++){
            new Thread(t,"Thread"+i).start();
        }
    }
}
