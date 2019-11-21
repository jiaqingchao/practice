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
    public static int WIDTH = ResourceMgr.redTankU.getWidth();
    public static int HEIGHT = ResourceMgr.redTankU.getHeight();
    private Dir dir = Dir.DOWN;
    private int speed = CONSTANTS.TANK_SPEED_5;
    private boolean moving = false;
    private boolean living = true;

    private Rectangle rectangle = new Rectangle();

    private TankFrame tf;
    Random random = new Random();

    private Group group;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        if(this.group == Group.AI){
            this.moving = true;
        }

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = Tank.WIDTH;
        rectangle.height = Tank.HEIGHT;
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
        return rectangle;
    }

    public void die() {
        this.living = false;
        int eX = this.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
        int eY = this.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;

        tf.Explodes.add(new Explode(eX, eY, tf));
    }

    public void paint(Graphics g) {
        switch (this.dir){
            case LEFT :
                g.drawImage(this.group == Group.RED?ResourceMgr.redTankL:ResourceMgr.aiTankL, this.x, this.y,null);
                break;
            case UP :
                g.drawImage(this.group == Group.RED?ResourceMgr.redTankU:ResourceMgr.aiTankU, this.x, this.y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.RED?ResourceMgr.redTankR:ResourceMgr.aiTankR, this.x, this.y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.RED?ResourceMgr.redTankD:ResourceMgr.aiTankD, this.x, this.y,null);
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

        // AI 随机发射子弹
        if(this.group == Group.AI && random.nextInt(100) > 95){
            this.fire();
        }
        //AI 随机改变方向
        if(this.group == Group.AI && random.nextInt(100) > 95)
            randomDir();

        boudsCheck();

        rectangle.x = this.x;
        rectangle.y = this.y;

        if(this.group == Group.RED) {
            new Thread(() -> new Audio("audio/tank_move.wav").play()).start();
        }
    }

    private void boudsCheck() {//边界检测

        if(this.x < 2) this.x = 2;
        if(this.y < 28) this.y = 28;
        if(this.x > CONSTANTS.WINDOW_WIDTH - Tank.WIDTH - 2) this.x = CONSTANTS.WINDOW_WIDTH - Tank.WIDTH - 2;
        if(this.y > CONSTANTS.WINDOW_HEIGHT - Tank.HEIGHT - 2) this.y = CONSTANTS.WINDOW_HEIGHT - Tank.HEIGHT - 2;
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

        if(this.group == Group.RED){
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
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
