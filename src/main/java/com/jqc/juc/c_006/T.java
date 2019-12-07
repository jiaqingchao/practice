package com.jqc.juc.c_006;

/**
 * 对比上面一个小程序，分析一下这个程序的输出
 * @author jqingchao
 */
public class T implements Runnable{
    private /*volatile*/ int count = 100;//加了 synchronized 不用加volatile
    public synchronized void run(){//加了synchronized保证一次只有一个线程进入，避免了上个程序的情况
        count--;
        System.out.println(Thread.currentThread().getName()+ ",count : "+ count);
    }

    public static void main(String[] args) {
        T t = new T();
        for(int i=0;i<100;i++){
            new Thread(t,"Thread"+i).start();
        }
    }
}
