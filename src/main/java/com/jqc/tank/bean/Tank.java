package com.jqc.tank.bean;

import com.jqc.tank.TankFrame;
import com.jqc.tank.common.CONSTANTS;
import com.jqc.tank.common.Dir;
import com.jqc.tank.common.TankType;

import java.awt.*;

public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private int speed;
    private boolean moving = false;
    private int width = 50;
    private int height = 50;
    private TankFrame tf;
    private boolean live = true;

    private TankType type = TankType.NORMAL;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf, TankType type) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        this.dir = dir;
        this.type = type;
        this.speed = type.getSpeed();
    }
    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);

        g.setColor(c);
        move();
    }

    private void move() {
        if(!moving) return;
        switch (dir){
            case LEFT : this.x -= speed;
                break;
            case UP : this.y -= speed;
                break;
            case RIGHT: this.x += speed;
                break;
            case DOWN: this.y += speed;
                break;
        }
    }
    public void fire(){
        int x = 0;
        int y = 0;

        int bulletSize = CONSTANTS.BULLET_SIZE_10;

        switch (this.dir){
            case DOWN:
                y = this.y + height;
                x = this.x + width/2 - bulletSize/2;
                break;
            case UP:
                y = this.y - bulletSize;
                x = this.x + width/2 - bulletSize/2;
                break;
            case LEFT:
                y = this.y + height/2 - bulletSize/2;
                x = this.x - bulletSize;
                break;
            case RIGHT:
                x = this.x + width;
                y = this.y + height/2 - bulletSize/2;
                break;
            default:
                break;
        }
        tf.bulletList.add(new Bullet(x, y, this.dir, this.tf));
    }
}
