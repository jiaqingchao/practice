package com.jqc.snake;

import java.awt.*;
import java.util.Random;

public class SnakePart {
    private int x;
    private int y;
    private Dir dir;
    private int speed = SnakeFrame.SNAKE_SPEED;
    private boolean moving = false;
    private boolean living = true;
    private Rectangle rectangle;
    private boolean isFood;

    private Random random = new Random();

    private SnakeFrame sf;

    public SnakePart(int x, int y, Dir dir, SnakeFrame sf){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.sf = sf;
    }

    public Rectangle getRectangle() {
        if(rectangle == null) return new Rectangle();
        return rectangle;
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
    public boolean isFood() {
        return isFood;
    }

    public void setFood(boolean food) {
        isFood = food;
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

    public void collisionWidth(SnakePart snakePart){
        Rectangle snakePartRectangle = snakePart.getRectangle();
        Rectangle snakeHeadRectangle = this.getRectangle();

        snakePartRectangle.setRect(snakePart.getX(), snakePart.getY(), SnakeFrame.SNAKE_WIDTH, SnakeFrame.SNAKE_WIDTH);
        snakeHeadRectangle.setRect(this.getX(), this.getY(), SnakeFrame.SNAKE_WIDTH, SnakeFrame.SNAKE_WIDTH);

        if(snakeHeadRectangle.intersects(snakePartRectangle)){
            if(snakePart.isFood()){
                SnakePart snakeTail = sf.snakeParts.get(sf.snakeParts.size() - 1);
                sf.snakeParts.add(new SnakePart(snakeTail.x,snakeTail.y,snakeTail.dir,snakeTail.sf));
                snakePart.randomFood();
                return;
            }
            this.setLiving(false);
        }

    }

    private void randomFood() {
        this.x = random.nextInt(SnakeFrame.WINDOW_WIDTH / 10) * 10;
        this.y = random.nextInt((SnakeFrame.WINDOW_HEIGHT - 30) / 10) * 10 + 30;

    }


}
