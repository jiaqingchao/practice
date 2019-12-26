package com.jqc.juc.c_026_00_interview.A1B2C3;

public class T06_00_cas {
    static Thread t1= null,t2=null;
    enum RunThread {T1,T2}
    //volatile保证runThread线程可见
    static volatile RunThread runThread = RunThread.T2; // 思考为什么用volatile
    public static void main(String[] args) {
        t1 = new Thread(()->{
            for (int c: CONSTANTS.numbers) {
                while(runThread != RunThread.T1) {}
                System.out.print(c);
                runThread = RunThread.T2;
            }
        });
        t2 = new Thread(()->{
            for (char c: CONSTANTS.letters) {
                while(runThread != RunThread.T2) {}
                System.out.print(c);
                runThread = RunThread.T1;
            }
        });
        t1.start();
        t2.start();
    }
}
