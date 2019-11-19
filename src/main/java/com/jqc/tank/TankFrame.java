package com.jqc.tank;

import com.jqc.tank.bean.Bullet;
import com.jqc.tank.bean.Tank;
import com.jqc.tank.common.CONSTANTS;
import com.jqc.tank.common.Dir;
import com.jqc.tank.common.TankType;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TankFrame extends Frame {

    public List<Tank> tankList = new ArrayList<>();

    public List<Bullet> bulletList = new ArrayList<>();

    public Tank myTank = new Tank(50,50, Dir.DOWN,this, TankType.PLAYER);

    public TankFrame() throws Exception {

        setVisible(true);
        setSize(CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT);
        setTitle("tank war");
        setResizable(false);

        addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        autoAddEnemy();

    }

    private void autoAddEnemy(){
        new Thread(()->{
            boolean addEnemy = true;
            long startTime = System.currentTimeMillis();
            while (myTank != null){

                if(tankList.size() >= 4) addEnemy = false;
                else addEnemy = true;

                if(addEnemy == false || System.currentTimeMillis() - startTime < 2000){
                    continue;
                }
                startTime = System.currentTimeMillis();

                int x = tankList.size() * 120;
                int y = tankList.size() * 100;
                Dir dir = Dir.DOWN;
                switch (tankList.size()){
                    case 0:
                        x = 50;
                        y = 500;
                        dir = Dir.UP;
                        break;
                    case 1:
                        x = 700;
                        y = 50;
                        dir = Dir.LEFT;
                        break;
                    case 2:
                        x = 700;
                        y = 500;
                        dir = Dir.LEFT;
                        break;
                    case 3:
                        x = 400;
                        y = 300;
                        dir = Dir.RIGHT;
                        break;
                    default:
                        break;

                }

                Tank enemyTank = new Tank(x,y,dir,this);
                tankList.add(enemyTank);

            }
        }).start();
    }

//  处理双缓冲，解决闪烁问题
    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0, 0, CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("敌方坦克的数量：" + tankList.size(), 10, 60);
        g.setColor(c);
        if(myTank == null){

            c = g.getColor();
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER", CONSTANTS.WINDOW_WIDTH / 2 - 50, CONSTANTS.WINDOW_HEIGHT / 2 - 10);

            g.setColor(c);
            return;
        }

        myTank.paint(g);

        ListIterator<Bullet> bullIterator = bulletList.listIterator();
        while(bullIterator.hasNext()){
            Bullet bullet = bullIterator.next();
            bullet.paint(g);

            if(!bullet.isLive()) bullIterator.remove();

        }

        ListIterator<Tank> tankListIterator = tankList.listIterator();
        while(tankListIterator.hasNext()){
            Tank tank = tankListIterator.next();
            tank.paint(g);

            if(!tank.isLive()) tankListIterator.remove(); // ConcurrentModificationException   //异步新增tank,数量对不上，导致报错

        }

    }

    class MyKeyListener extends KeyAdapter{

        boolean bL;
        boolean bU;
        boolean bR;
        boolean bD;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case  KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir(myTank);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case  KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                default:
                    break;
            }
            setMainTankDir(myTank);
        }

        private void setMainTankDir(Tank tank){
            if(!bL && !bU && !bR && !bD) tank.setMoving(false);
            else tank.setMoving(true);

            if(bL) tank.setDir(Dir.LEFT);
            if(bU) tank.setDir(Dir.UP);
            if(bR) tank.setDir(Dir.RIGHT);
            if(bD) tank.setDir(Dir.DOWN);
        }
    }


}
