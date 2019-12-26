package com.jqc.designpattern.singleton;

public class MyMgr {
    private static final MyMgr INSTANCE = new MyMgr();

    private MyMgr() {
    }

    ;

    public static MyMgr getInstance() {
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    private void n() {
        System.out.println("n");
    }
}
