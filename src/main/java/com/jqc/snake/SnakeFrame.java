package com.jqc.snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;

public class SnakeFrame extends Frame {

    public final static int WINDOW_WIDTH = 600;
    public final static int WINDOW_HEIGHT = 400;
    public final static int SNAKE_WIDTH = 10;
    public final static int SNAKE_SPEED = 10;

    public List<SnakePart> snakeParts = new ArrayList();
    public SnakePart snakeHead;

    SnakePart food;


    public SnakeFrame() {
        this.setVisible(true);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addKeyListener(new SnakeKeyAdapter());

    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(WINDOW_WIDTH, WINDOW_HEIGHT);
        }

        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);

        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {

        if (snakeParts.size() == 0) initSnake();

        if (!snakeHead.isLiving()) {
            Color c = g.getColor();
            g.setColor(Color.white);
            g.drawString("GAME OVER", WINDOW_WIDTH / 2 - 80, WINDOW_HEIGHT / 2 - 10);
            g.setColor(c);
            return;
        }

        food.paint(g);
        for (SnakePart snakePart : snakeParts) {
            snakePart.paint(g);
        }

        for (SnakePart snakePart : snakeParts) {
            snakeHead.collisionWidth(snakePart);
        }
        snakeHead.collisionWidth(food);

        if (!snakeHead.isMoving()) return;
        setSnakePartsXY();
        snakeHead.move();

    }

    private void setSnakePartsXY() {
        for (int i = snakeParts.size() - 1; i >= 0; i--) {
            SnakePart thisSnakePart = snakeParts.get(i);
            if (i != 0) {
                SnakePart lastSnakePart = snakeParts.get(i - 1);
                thisSnakePart.setX(lastSnakePart.getX());
                thisSnakePart.setY(lastSnakePart.getY());
            } else {
                thisSnakePart.setX(snakeHead.getX());
                thisSnakePart.setY(snakeHead.getY());
            }
        }
    }

    private void initSnake() {
        food = new SnakePart(300, 200, Dir.DOWN, this);
        food.setFood(true);
        for (int i = 0; i < 8; i++) {
            SnakePart snakePart = new SnakePart(80 - SnakeFrame.SNAKE_WIDTH * i, 80, Dir.RIGHT, this);
            if (i == 0)
                snakeHead = snakePart;
            else
                snakeParts.add(snakePart);

        }
    }

    class SnakeKeyAdapter extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                default:
                    break;
            }
            setMainSankeDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                default:
                    break;
            }

            setMainSankeDir();
        }

        private void setMainSankeDir() {

            if (!snakeHead.isMoving())
                snakeHead.setMoving(true);

            if (bL && snakeHead.getDir() != Dir.RIGHT) snakeHead.setDir(Dir.LEFT);
            if (bU && snakeHead.getDir() != Dir.DOWN) snakeHead.setDir(Dir.UP);
            if (bR && snakeHead.getDir() != Dir.LEFT) snakeHead.setDir(Dir.RIGHT);
            if (bD && snakeHead.getDir() != Dir.UP) snakeHead.setDir(Dir.DOWN);
        }

    }
}
