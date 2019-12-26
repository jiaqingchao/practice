package com.jqc.designpattern.allDP;

public class DP01_Singleton {
    public static void main(String[] args) {
    }
}

class Singleton_01 {
    private final static Singleton_01 INSTANCE = new Singleton_01();

    private Singleton_01() {
    }

    public Singleton_01 getInstance() {
        return INSTANCE;
    }
}

class Singleton_02 {

    private Singleton_02() {
    }

    public Singleton_02 getInstance() {
        return Singleton_02_Private.INSTANCE;
    }

    static class Singleton_02_Private {
        private static final Singleton_02 INSTANCE = new Singleton_02();
    }
}

enum Singleton_03 {
    INSTANCE
}