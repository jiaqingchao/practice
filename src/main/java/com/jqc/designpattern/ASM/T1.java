package com.jqc.designpattern.ASM;

public class T1 {
    int i = 0;
    public static void m(){
        int j = 1;
        int k = 0;
        k = k++;
        System.out.println(k);
    }

    public static void main(String[] args) {
        m();
    }
}
