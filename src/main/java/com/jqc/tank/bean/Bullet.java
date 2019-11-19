package com.jqc.tank.bean;

import com.jqc.tank.TankFrame;
import com.jqc.tank.common.BulletType;
import com.jqc.tank.common.CONSTANTS;
import com.jqc.tank.common.Dir;

import java.awt.*;
import java.util.ListIterator;

public class Bullet {

    private int x;
    private int y;
    private int speed;
    private Dir dir;

    private boolean live = true;
    private TankFrame tf;

    private BulletType type;
    private int power;
    private int size;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;

        this.type = BulletType.NORMAL;
        this.power = type.getPower();
        this.size = type.getPower();
        this.speed = type.getSpeed();
    }

    public Bullet(int x,int y, Dir dir, BulletType type) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.type = type;
        this.power = type.getPower();
        this.size = type.getPower();
        this.speed = type.getSpeed();
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isLive() {
        return live;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, CONSTANTS.BULLET_SIZE_10, CONSTANTS.BULLET_SIZE_10);
        g.setColor(c);
        move();

    }
    public void move() {
        switch (dir){

            case LEFT :
                this.x -= speed;
                break;

            case UP :
                this.y -= speed;
                break;

            case RIGHT:
                this.x += speed;
                break;

            case DOWN: this.y += speed;
                break;

        }

        if(this.x < 0 || this.y < 0
                || this.x > CONSTANTS.WINDOW_WIDTH
                || this.y > CONSTANTS.WINDOW_HEIGHT)
            live = false;

        checkDestroyTank();
        //checkMyTank();
    }

    private void checkDestroyTank() {

        for(ListIterator<Tank> iterators = tf.tankList.listIterator();iterators.hasNext();){
            Tank tank = iterators.next();
            int tx = tank.getX();
            int ty = tank.getY();
            int tw = tank.getWidth();
            int th = tank.getHeight();
            if((this.x - CONSTANTS.BULLET_SIZE_10 > tx && this.x - CONSTANTS.BULLET_SIZE_10 < tx + tw)
                    &&(this.y - CONSTANTS.BULLET_SIZE_10 > ty && this.y- CONSTANTS.BULLET_SIZE_10 < ty + th)){
                this.live = false;
                tank.setLive(false);
            }
        }
    }


    private void checkMyTank() {

        Tank tank = tf.myTank;
        int tx = tank.getX();
        int ty = tank.getY();
        int tw = tank.getWidth();
        int th = tank.getHeight();
        if((this.x - CONSTANTS.BULLET_SIZE_10 > tx && this.x - CONSTANTS.BULLET_SIZE_10 < tx + tw)
                ||(this.y - CONSTANTS.BULLET_SIZE_10 > ty && this.y- CONSTANTS.BULLET_SIZE_10 < ty + th)){
            //tf.myTank = isLive();
        }

    }

    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                ", power=" + power +
                ", size=" + size +
                ", speed=" + speed +
                ", dir=" + dir +
                '}';
    }
}
