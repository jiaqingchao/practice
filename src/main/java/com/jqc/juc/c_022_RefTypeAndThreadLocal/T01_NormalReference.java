package com.jqc.juc.c_022_RefTypeAndThreadLocal;

import java.io.IOException;

public class T01_NormalReference {
    public static void main(String[] args) {
        M m = new M();
        m = null; //去掉引用，gc 回收 M实例
        System.gc(); // DisableExplicitGC

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
