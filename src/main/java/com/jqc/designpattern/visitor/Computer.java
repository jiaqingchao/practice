package com.jqc.designpattern.visitor;

public class Computer {
    ComputerPart cpu = new CPU();
    ComputerPart memory = new Memory();
    ComputerPart board = new Board();

    public void accept(Visitor v) {
        this.cpu.accept(v);
        this.memory.accept(v);
        this.board.accept(v);
    }

    public static void main(String[] args) {
        Visitor v = new CorpVisitor();
        new Computer().accept(v);
        System.out.println(v.getTotalPrive());
    }
}

abstract class ComputerPart {
    abstract void accept(Visitor v);

    abstract double getPrice();
}

class CPU extends ComputerPart {
    @Override
    void accept(Visitor v) {
        v.visitCpu(this);
    }

    @Override
    double getPrice() {
        return 500;
    }
}

class Memory extends ComputerPart {

    @Override
    void accept(Visitor v) {
        v.visitMemory(this);
    }

    @Override
    double getPrice() {
        return 300;
    }
}

class Board extends ComputerPart {

    @Override
    void accept(Visitor v) {
        v.visitBoard(this);
    }

    @Override
    double getPrice() {
        return 200;
    }
}

abstract class Visitor {

    abstract void visitCpu(CPU cpu);

    abstract void visitMemory(Memory memory);

    abstract void visitBoard(Board board);

    abstract double getTotalPrive();
}

class PersonelVisitor extends Visitor {
    public double totalPrive = 0;

    @Override
    void visitCpu(CPU cpu) {
        totalPrive += cpu.getPrice() * 0.9;
    }

    @Override
    void visitMemory(Memory memory) {
        totalPrive += memory.getPrice() * 0.85;
    }

    @Override
    void visitBoard(Board board) {
        totalPrive += board.getPrice() * 0.95;
    }

    @Override
    public double getTotalPrive() {
        return totalPrive;
    }
}

class CorpVisitor extends Visitor {
    public double totalPrive = 0;

    @Override
    void visitCpu(CPU cpu) {
        totalPrive += cpu.getPrice() * 0.6;
    }

    @Override
    void visitMemory(Memory memory) {
        totalPrive += memory.getPrice() * 0.75;
    }

    @Override
    void visitBoard(Board board) {
        totalPrive += board.getPrice() * 0.75;
    }

    @Override
    public double getTotalPrive() {
        return totalPrive;
    }
}