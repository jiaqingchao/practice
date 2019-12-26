package com.jqc.designpattern.allDP;

public class Visitor_ {

    public static void main(String[] args) {
        System.out.println(new Computer().accept(new PersonalVisitor()));
        ;
        System.out.println(new Computer().accept(new CorpVisitor()));
        ;
    }
}

class Computer {
    CommputerPart c = new CPU();
    CommputerPart m = new Memory();
    CommputerPart b = new Board();

    public double accept(Visitor v) {
        c.accept(v);
        m.accept(v);
        b.accept(v);
        return v.getTotalPrice();
    }

}

abstract class Visitor {
    abstract double getTotalPrice();

    abstract void visitMemory(Memory m);

    abstract void visitBoard(Board b);

    abstract void visitCpu(CPU c);
}

class PersonalVisitor extends Visitor {
    double totalPrice = 0;

    @Override
    double getTotalPrice() {
        return totalPrice;
    }

    @Override
    void visitMemory(Memory m) {
        totalPrice += m.getPrice() * 0.7;
    }

    @Override
    void visitBoard(Board b) {
        totalPrice += b.getPrice() * 0.8;
    }

    @Override
    void visitCpu(CPU c) {
        totalPrice += c.getPrice() * 0.9;
    }
}

class CorpVisitor extends Visitor {
    double totalPrice = 0;

    @Override
    double getTotalPrice() {
        return totalPrice;
    }

    @Override
    void visitMemory(Memory m) {
        totalPrice += m.getPrice() * 0.4;
    }

    @Override
    void visitBoard(Board b) {
        totalPrice += b.getPrice() * 0.5;
    }

    @Override
    void visitCpu(CPU c) {
        totalPrice += c.getPrice() * 0.6;
    }
}

abstract class CommputerPart {
    public abstract double getPrice();

    public abstract void accept(Visitor v);
}

class Memory extends CommputerPart {
    @Override
    public double getPrice() {
        return 300;
    }

    @Override
    public void accept(Visitor v) {
        v.visitMemory(this);
    }
}

class CPU extends CommputerPart {
    @Override
    public double getPrice() {
        return 500;
    }

    @Override
    public void accept(Visitor v) {
        v.visitCpu(this);
    }
}

class Board extends CommputerPart {
    @Override
    public double getPrice() {
        return 200;
    }

    @Override
    public void accept(Visitor v) {
        v.visitBoard(this);
    }
}