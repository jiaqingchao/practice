package com.jqc.snake;

import java.awt.*;

public class SnakePart {
    private int x;
    private int y;
    private Dir dir;
    private int speed = SnakeFrame.SNAKE_SPEED;
    public static int WIDTH = SnakeFrame.WIDTH;
    private boolean moving = false;
    private boolean living = true;

    private SnakeFrame sf;

    public SnakePart(int x, int y, Dir dir, SnakeFrame sf){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.sf = sf;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.white);
        g.fillRect(x, y, SnakeFrame.SNAKE_WIDTH, SnakeFrame.SNAKE_WIDTH);
        g.setColor(c);
    }

    public void move() {
        switch (this.dir){
            case DOWN:
                this.y += this.speed;
                break;
            case UP:
                this.y -= this.speed;
                break;
            case RIGHT:
                this.x += this.speed;
                break;
            case LEFT:
                this.x -= this.speed;
                break;
            default:
                break;
        }

        if(this.x < 0 || this.x + SnakeFrame.SNAKE_WIDTH > SnakeFrame.WINDOW_WIDTH
            ||this.y < 0 || this.y + SnakeFrame.SNAKE_WIDTH > SnakeFrame.WINDOW_HEIGHT){
            this.setLiving(false);
        }
    }


}
