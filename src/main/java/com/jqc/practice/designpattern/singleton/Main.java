package com.jqc.designPattern.singleton;

public class Main {
    public static void main(String[] args) {
        MyMgr m = MyMgr.getInstance();
       // MyMgr m1 = new MyMgr();
        m.m();
    }
}

