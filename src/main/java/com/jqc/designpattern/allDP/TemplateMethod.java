package com.jqc.designpattern.allDP;

public class TemplateMethod {
    public static void main(String[] args) {
        M m =new M1();
        m.m();
    }
}
abstract class M{
    public void m(){
        op1();
        op2();
    }
    abstract void op1();
    abstract void op2();
}
class M1 extends M{
    @Override
    public void op1() {
        System.out.println("op1");
    }
    @Override
    public void op2() {
        System.out.println("op2");
    }
}
