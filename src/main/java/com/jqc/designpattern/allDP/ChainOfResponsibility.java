package com.jqc.designpattern.allDP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChainOfResponsibility {
    public static void main(String[] args) {
        ColliderChain chain = ColliderChain.INSTANCE;

        //Facde
        List<GameObject_05> gameObjs = new ArrayList<>();
        gameObjs.add(new Tank_05());
        gameObjs.add(new Tank_05());
        gameObjs.add(new Bullet_05());
        gameObjs.add(new Bullet_05());
        gameObjs.add(new Explode_05());
        gameObjs.add(new Explode_05());

        for (int i = 0; i < gameObjs.size() - 1; i++) {
            GameObject_05 g1 = gameObjs.get(i);
            for (int j = i + 1; j < gameObjs.size(); j++) {
                GameObject_05 g2 = gameObjs.get(i);
                if (!chain.collisionWidth(g1, g2)) {
                    continue;
                }
            }
        }

    }
}

interface Collider_05<G1, G2> {
    boolean collisionWidth(G1 g1, G2 g2);
}

class ColliderChain implements Collider_05<GameObject_05, GameObject_05> {
    public static final ColliderChain INSTANCE = new ColliderChain();

    List<Collider_05> colliders = new LinkedList<>();

    public void add(Collider_05 c) {
        colliders.add(c);
    }

    private ColliderChain() {
        //通过配置文件加反射，可以做到不修改这部分代码
        add(new TankBulletCollider_05());
        add(new TankWallCollider_05());
        add(new BulletWallCollider_05());
        add(new TankWallCollider_05());
    }

    public boolean collisionWidth(GameObject_05 g1, GameObject_05 g2) {
        for (Collider_05 c : colliders) {
            if (!c.collisionWidth(g1, g2)) {
                return false;
            }
        }
        return true;
    }
}

class TankBulletCollider_05 implements Collider_05<GameObject_05, GameObject_05> {
    @Override
    public boolean collisionWidth(GameObject_05 g1, GameObject_05 g2) {
        if (g1 instanceof Tank_05 && g2 instanceof Bullet_05) {
            System.out.println("Tank and bullet Collider_05, tank die, bullet die");
            return true;
        } else if (g1 instanceof Bullet_05 && g2 instanceof Tank_05) {
            collisionWidth(g2, g1);
        }
        return true;
    }
}

class TankWallCollider_05 implements Collider_05<GameObject_05, GameObject_05> {
    @Override
    public boolean collisionWidth(GameObject_05 g1, GameObject_05 g2) {
        if (g1 instanceof Tank_05 && g2 instanceof Wall_05) {
            System.out.println("Tank and Wall Collider_05, tank back");
            return true;
        }
        return true;
    }
}

class BulletWallCollider_05 implements Collider_05<GameObject_05, GameObject_05> {
    @Override
    public boolean collisionWidth(GameObject_05 g1, GameObject_05 g2) {
        if (g1 instanceof Bullet_05 && g2 instanceof Wall_05) {
            System.out.println("Tank and Wall Collider_05, bullet die ");
            return true;
        }
        return true;
    }
}

class TankTankCollider_05 implements Collider_05<GameObject_05, GameObject_05> {
    @Override
    public boolean collisionWidth(GameObject_05 g1, GameObject_05 g2) {
        if (g1 instanceof Tank_05 && g2 instanceof Tank_05) {
            System.out.println("Tank and Tank Collider_05, Tank all stop ");
            return true;
        }
        return true;
    }
}

abstract class GameObject_05 {
    public abstract void paint();

}

class Tank_05 extends GameObject_05 {
    @Override
    public void paint() {
        System.out.println("paint one tank");
    }
}

class Bullet_05 extends GameObject_05 {
    @Override
    public void paint() {
        System.out.println("paint one bullet");
    }
}

class Explode_05 extends GameObject_05 {
    @Override
    public void paint() {
        System.out.println("paint one explode");
    }
}

class Wall_05 extends GameObject_05 {
    @Override
    public void paint() {
        System.out.println("paint one explode");
    }
}