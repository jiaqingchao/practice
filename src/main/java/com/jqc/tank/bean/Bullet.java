package com.jqc.tank.bean;

import com.jqc.tank.TankFrame;
import com.jqc.tank.common.CONSTANTS;
import com.jqc.tank.common.Dir;
import com.jqc.tank.common.Group;
import com.jqc.tank.common.ResourceMgr;

import java.awt.*;

public class Bullet {

    private int x;
    private int y;
    private int speed = CONSTANTS.BULLET_SPEED_10;
    private Dir dir;

    public static int WIDTH = ResourceMgr.bulletU.getWidth();
    public static int HEIGHT = ResourceMgr.bulletU.getHeight();

    private boolean living = true;
    private TankFrame tf;

    private Group group;
    private int power;
    private int size;

    private Rectangle rectangle = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = Tank.WIDTH;
        rectangle.height = Tank.HEIGHT;
    }

    public boolean isLiving() {
        return living;
    }

    private void die() {
        this.living = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void paint(Graphics g) {
        switch (this.dir){
            case LEFT :
                g.drawImage(ResourceMgr.bulletL, this.x, this.y,null);
                break;
            case UP :
                g.drawImage(ResourceMgr.bulletU, this.x, this.y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, this.x, this.y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, this.x, this.y,null);
                break;
            default:
                break;
        }
        move();

    }
    public void move() {
        switch (this.dir){
            case LEFT :
                this.x -= this.speed;
                break;
            case UP :
                this.y -= this.speed;
                break;
            case RIGHT:
                this.x += this.speed;
                break;
            case DOWN: this.y += this.speed;
                break;
            default:
                break;
        }

        rectangle.x = this.x;
        rectangle.y = this.y;

        if(this.x < 0 || this.x > CONSTANTS.WINDOW_WIDTH
                || this.y < 0 || this.y > CONSTANTS.WINDOW_HEIGHT)
            this.die();


    }

    public void collisionWidth(Tank tank) {
        if(this.group == tank.getGroup()) return;

        Rectangle tankRect = tank.getRectangle();
        Rectangle bulletRect = this.getRectangle();

        if(bulletRect.intersects(tankRect)){
            this.die();
            tank.die();
        }

    }
}
