package com.jqc.designpattern.allDP;

import java.util.ArrayList;
import java.util.List;

public class Mediator {
    public static void main(String[] args) {
        Collider<GameObject_04, GameObject_04> TBCollider = new TankBulletCollider();
        Collider<GameObject_04, GameObject_04> TWCollider = new TankWallCollider();
        Collider<GameObject_04, GameObject_04> BWCollider = new BulletWallCollider();
        Collider<GameObject_04, GameObject_04> TTCollider = new TankTankCollider();

        //Facde
        List<GameObject_04> gameObjs = new ArrayList<>();
        gameObjs.add(new Tank_04());
        gameObjs.add(new Tank_04());
        gameObjs.add(new Bullet_04());
        gameObjs.add(new Bullet_04());
        gameObjs.add(new Explode_04());
        gameObjs.add(new Explode_04());

        for (int i = 0; i < gameObjs.size() - 1; i++) {
            GameObject_04 g1 = gameObjs.get(i);
            for (int j = i + 1; j < gameObjs.size(); j++) {
                GameObject_04 g2 = gameObjs.get(i);
                TBCollider.collisionWidth(g1, g2);
                TWCollider.collisionWidth(g1, g2);
                BWCollider.collisionWidth(g1, g2);
                TTCollider.collisionWidth(g1, g2);
            }
        }

    }
}

interface Collider<G1, G2> {
    void collisionWidth(G1 g1, G2 g2);
}

class TankBulletCollider implements Collider<GameObject_04, GameObject_04> {
    @Override
    public void collisionWidth(GameObject_04 g1, GameObject_04 g2) {
        if (g1 instanceof Tank_04 && g2 instanceof Bullet_04) {
            System.out.println("Tank and bullet Collider, tank die, bullet die");
        } else if (g1 instanceof Bullet_04 && g2 instanceof Tank_04) {
            collisionWidth(g2, g1);
        }
    }
}

class TankWallCollider implements Collider<GameObject_04, GameObject_04> {
    @Override
    public void collisionWidth(GameObject_04 g1, GameObject_04 g2) {
        if (g1 instanceof Tank_04 && g2 instanceof Wall) {
            System.out.println("Tank and Wall Collider, tank back");
        }
    }
}

class BulletWallCollider implements Collider<GameObject_04, GameObject_04> {
    @Override
    public void collisionWidth(GameObject_04 g1, GameObject_04 g2) {
        if (g1 instanceof Bullet_04 && g2 instanceof Wall) {
            System.out.println("Tank and Wall Collider, bullet die ");
        }
    }
}

class TankTankCollider implements Collider<GameObject_04, GameObject_04> {
    @Override
    public void collisionWidth(GameObject_04 g1, GameObject_04 g2) {
        if (g1 instanceof Tank_04 && g2 instanceof Tank_04) {
            System.out.println("Tank and Tank Collider, Tank all stop ");
        }
    }
}

abstract class GameObject_04 {
    public abstract void paint();
}

class Tank_04 extends GameObject_04 {
    @Override
    public void paint() {
        System.out.println("paint one tank");
    }
}

class Bullet_04 extends GameObject_04 {
    @Override
    public void paint() {
        System.out.println("paint one bullet");
    }
}

class Explode_04 extends GameObject_04 {
    @Override
    public void paint() {
        System.out.println("paint one explode");
    }
}

class Wall extends GameObject_04 {
    @Override
    public void paint() {
        System.out.println("paint one explode");
    }
}