package com.jqc.tank.bean;

import com.jqc.tank.TankFrame;
import com.jqc.tank.common.CONSTANTS;
import com.jqc.tank.common.Dir;
import com.jqc.tank.common.ResourceMgr;
import com.jqc.tank.common.Group;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    public static int WIDTH = ResourceMgr.tankU.getWidth();
    public static int HEIGHT = ResourceMgr.tankU.getHeight();
    private Dir dir = Dir.DOWN;
    private int speed = CONSTANTS.TANK_SPEED_5;
    private boolean moving = false;
    private boolean living = true;

    private Rectangle rectangle;

    private TankFrame tf;
    Random random = new Random();

    private Group group;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        if(this.group == Group.AI)
            this.moving = true;
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

    public boolean isLiving() {
        return living;
    }

    public Group getGroup() {
        return group;
    }

    public Rectangle getRectangle(){
        if(rectangle == null){
            rectangle = new Rectangle();
        }
        return rectangle;
    }

    public void die() {
        this.living = false;
        tf.Explodes.add(new Explode(this.x - (Explode.WIDTH - Tank.WIDTH) / 2,
                this.y - (Explode.HEIGHT - Tank.HEIGHT), tf));
    }

    public void paint(Graphics g) {
        switch (this.dir){
            case LEFT :
                g.drawImage(ResourceMgr.tankL, this.x, this.y,null);
                break;
            case UP :
                g.drawImage(ResourceMgr.tankU, this.x, this.y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, this.x, this.y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, this.x, this.y,null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        if(!this.moving) return;

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
            case DOWN:
                this.y += this.speed;
                break;
            default:
                break;
        }
        if(this.x < 0) this.x = 0;
        if(this.y < 0) this.y = 0;
        if(this.x > CONSTANTS.WINDOW_WIDTH - Tank.WIDTH) this.x = CONSTANTS.WINDOW_WIDTH - Tank.WIDTH;
        if(this.y > CONSTANTS.WINDOW_HEIGHT - Tank.HEIGHT) this.y = CONSTANTS.WINDOW_HEIGHT - Tank.HEIGHT;

        if(this.group == Group.AI){
            if(random.nextInt(100) > 95){
                this.fire();
                randomDir();
            }
        }

    }

    public void fire(){
        int bX = 0;
        int bY = 0;

        int bulletWidth = Bullet.WIDTH;
        int bulletHeight = Bullet.HEIGHT;
        switch (this.dir){
            case DOWN:
                bY = this.y + Tank.HEIGHT;
                bX = this.x + Tank.WIDTH/2 - bulletWidth/2;
                break;
            case UP:
                bY = this.y - bulletHeight;
                bX = this.x + Tank.WIDTH/2 - bulletWidth/2;
                break;
            case LEFT:
                bY = this.y + Tank.HEIGHT/2 - bulletHeight/2;
                bX = this.x - bulletWidth;
                break;
            case RIGHT:
                bX = this.x + Tank.WIDTH;
                bY = this.y + Tank.HEIGHT/2 - bulletHeight/2;
                break;
            default:
                break;
        }
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));
    }
    private void randomDir() {
        int randomNum = random.nextInt(4);
        switch (randomNum){
            case 0 :
                dir = Dir.UP;
                break;
            case 1 :
                dir = Dir.DOWN;
                break;
            case 2:
                dir = Dir.LEFT;
                break;
            case 3:
                dir = Dir.RIGHT;
                break;
            default:
                break;
        }
    }

}
