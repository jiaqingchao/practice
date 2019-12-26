package com.jqc.designpattern.allDP;

public class Decorator {
    public static void main(String[] args) {
        new TailDecorator(new RectDecorator(new Tank06()))
                .paint();
        new TailDecorator(new RectDecorator(new Bullet06()))
                .paint();
        new TailDecorator(new RectDecorator(new Explode06()))
                .paint();

    }
}

abstract class GameObject06 {
    public abstract void paint();
}

class Tank06 extends GameObject06 {
    @Override
    public void paint() {
        System.out.println("paint one tank");
    }
}

class Bullet06 extends GameObject06 {
    @Override
    public void paint() {
        System.out.println("paint one bullet");
    }
}

class Explode06 extends GameObject06 {
    @Override
    public void paint() {
        System.out.println("paint one explode");
    }
}

abstract class GoDecorator extends GameObject06 {
    GameObject06 go;

    public GoDecorator(GameObject06 go) {
        this.go = go;
    }
}

class RectDecorator extends GoDecorator {
    public RectDecorator(GameObject06 go) {
        super(go);
    }

    @Override
    public void paint() {
        System.out.println("paint rect");
        go.paint();
    }
}


class TailDecorator extends GoDecorator {
    public TailDecorator(GameObject06 go) {
        super(go);
    }

    @Override
    public void paint() {
        System.out.println("paint tail");
        go.paint();
    }
}
