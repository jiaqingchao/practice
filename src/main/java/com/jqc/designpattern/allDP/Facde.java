package com.jqc.designpattern.allDP;

import java.util.ArrayList;
import java.util.List;

public class Facde {
    public static void main(String[] args) {
        //
        List<Tank_03> tank03s = new ArrayList<>();
        List<Bullet_03> bullet03s = new ArrayList<>();
        List<Explode_03> explode03s = new ArrayList<>();

        tank03s.add(new Tank_03());
        tank03s.add(new Tank_03());
        tank03s.forEach(tank_03 -> tank_03.paint());

        bullet03s.add(new Bullet_03());
        bullet03s.add(new Bullet_03());
        bullet03s.forEach(bullet03 -> bullet03.paint());

        explode03s.add(new Explode_03());
        explode03s.add(new Explode_03());
        explode03s.forEach(explode03 -> explode03.paint());

        System.out.println("==========");
        //Facde
        List<GameObject_03> gameObjs = new ArrayList<>();
        gameObjs.add(new Tank_03());
        gameObjs.add(new Tank_03());
        gameObjs.add(new Bullet_03());
        gameObjs.add(new Bullet_03());
        gameObjs.add(new Explode_03());
        gameObjs.add(new Explode_03());
        gameObjs.forEach(gameObj -> gameObj.paint());

    }
}

abstract class GameObject_03 {
    public abstract void paint();
}

class Tank_03 extends GameObject_03 {
    @Override
    public void paint() {
        System.out.println("paint one tank");
    }
}

class Bullet_03 extends GameObject_03 {
    @Override
    public void paint() {
        System.out.println("paint one bullet");
    }
}

class Explode_03 extends GameObject_03 {
    @Override
    public void paint() {
        System.out.println("paint one explode");
    }
}
