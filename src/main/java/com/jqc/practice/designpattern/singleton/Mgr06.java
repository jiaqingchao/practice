package com.jqc.designPattern.singleton;

/**
 * lazy loading
 * 也称懒汉式
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * 可以通过Synchronized解决，但也带来效率下降
 *
 * cpu指令重排问题，几百万次不一定有一次
 */
public class Mgr06 {
    private static Mgr06 INSTANCE;
    // private volatile static com.jqc.designPattern.singleton.Mgr06 INSTANCE;
    // volatile禁止指令重排,解决指令重排的问题
    private Mgr06(){}
    public static Mgr06 getInstance(){
        if(INSTANCE == null){
            //双重检查
            synchronized(Mgr06.class){
                if(INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr06();
                }
            }
        }
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            new Thread(()->{
                System.out.println(Mgr06.getInstance().hashCode());
            }).start();
        }
    }
}

