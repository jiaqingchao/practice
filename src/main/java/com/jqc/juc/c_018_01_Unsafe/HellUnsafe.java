package com.jqc.juc.c_018_01_Unsafe;

import sun.misc.Unsafe;

public class HellUnsafe {
    static class M {
        int i = 0;

        private M() {

        }
    }

    public static void main(String[] args) {
        Unsafe unsafe = Unsafe.getUnsafe();//启动报错-> SecurityException
        M m = null;
        try {
            m = (M) unsafe.allocateInstance(M.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        m.i = 9;
        System.out.println(m.i);
    }
}
