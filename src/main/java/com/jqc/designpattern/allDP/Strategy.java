package com.jqc.designpattern.allDP;

public class Strategy {
    public static void main(String[] args) {
        new Tank_01(new DefaultFireStartegy()).fire();
        new Tank_01(new SquareFireStartegy()).fire();
        new Tank_01(new NBombFireStartegy()).fire();
    }
}
interface FireStrategy<T> {
    void fire(T t);
}
class DefaultFireStartegy implements FireStrategy<Tank_01> {
    @Override
    public void fire(Tank_01 t) {
        System.out.println("fire forward...");
    }
}
class SquareFireStartegy implements FireStrategy<Tank_01> {
    @Override
    public void fire(Tank_01 t) {
        System.out.println("fire on all sides...");
    }
}
class NBombFireStartegy implements FireStrategy<Tank_01> {
    @Override
    public void fire(Tank_01 t) {
        System.out.println("issue a nuclear bomb");
    }
}
class Tank_01 {
    FireStrategy fs;

    public Tank_01(FireStrategy fs) {
        this.fs = fs;
    }
    void fire(){
        fs.fire(this);
    }
}