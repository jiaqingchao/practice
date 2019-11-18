package com.jqc.designPattern.singleton;

/**
 * 枚举单例
 * 不仅可以解决线程同步，还可以防止反序列化
 * java 可以通过反射（反序列化）的方式 newInstance()方式new一个新的对象，
 * 枚举没有构造方法，不能通过反序列化得方式构造对象。
 * 不过一个类定义为一个枚举还是很别扭的。
 */
public enum Mgr08 {
    INSTSNCE;

    public void m(){
        System.out.println("m");
    }


    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            new Thread(()->{
                System.out.println(Mgr08.INSTSNCE.hashCode());
            }).start();
        }
    }
}
