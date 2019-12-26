package com.jqc.juc.c_021_03_VarHandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class T01_HelloVarHandle {
    int x = 8;
    private static VarHandle handle;//可以做原子性的修改值，CAS

    static {
        try {
            handle = MethodHandles.lookup().findVarHandle(T01_HelloVarHandle.class, "x", int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T01_HelloVarHandle t = new T01_HelloVarHandle();
        handle.set(t, 9);
        System.out.println((int) handle.get(t));


        handle.compareAndSet(t, 9, 10);
        System.out.println(t.x);

        handle.getAndAdd(t, 10);
        System.out.println(t.x);


    }
}
