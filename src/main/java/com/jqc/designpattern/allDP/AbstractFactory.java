package com.jqc.designpattern.allDP;

class Tank_02 extends GameObject {
    @Override
    public void paint() {
        System.out.println("created one tank");
    }
}

class Bullet_02 extends GameObject {
    @Override
    public void paint() {
        System.out.println("created one bullet");
    }
}

interface GameFactory<G> {
    G createGameObj();
}

abstract class GameObject {
    public abstract void paint();
}

class TankFactory implements GameFactory<GameObject> {
    @Override
    public GameObject createGameObj() {
        return new Tank_02();
    }
}

class BulletFactory implements GameFactory<GameObject> {
    @Override
    public GameObject createGameObj() {
        return new Bullet_02();
    }
}

class DefaultTank_02 extends Tank_02 {
    @Override
    public void paint() {
        System.out.println("created one DefaultTank");
    }
}

class DefaultBullet_02 extends Bullet_02 {
    @Override
    public void paint() {
        System.out.println("created one DefaultBullet");
    }
}

class RectTank_02 extends Tank_02 {
    @Override
    public void paint() {
        System.out.println("created one RectTank");
    }
}

class RectBullet_02 extends Bullet_02 {
    @Override
    public void paint() {
        System.out.println("created one RectBullet");
    }
}

interface GameGroupFactory<T> {
    Tank_02 createTank();

    Bullet_02 createBullet();
}

class DefaultFactory implements GameGroupFactory {
    @Override
    public Tank_02 createTank() {
        return new DefaultTank_02();
    }

    @Override
    public Bullet_02 createBullet() {
        return new DefaultBullet_02();
    }
}

class RectFactory implements GameGroupFactory {
    @Override
    public Tank_02 createTank() {
        return new RectTank_02();
    }

    @Override
    public Bullet_02 createBullet() {
        return new RectBullet_02();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        GameFactory<GameObject> gf = new TankFactory();
        gf.createGameObj().paint();

        gf = new BulletFactory();
        gf.createGameObj().paint();

        GameGroupFactory<GameObject> ggf = new DefaultFactory();
        ggf.createTank().paint();
        ggf.createBullet().paint();

        ggf = new RectFactory();
        ggf.createTank().paint();
        ggf.createBullet().paint();

    }
}
