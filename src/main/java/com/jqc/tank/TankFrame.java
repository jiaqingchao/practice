package com.jqc.tank;

import com.jqc.tank.bean.Explode;
import com.jqc.tank.bean.Bullet;
import com.jqc.tank.bean.Tank;
import com.jqc.tank.common.CONSTANTS;
import com.jqc.tank.common.Dir;
import com.jqc.tank.common.Group;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TankFrame extends Frame {

    public List<Tank> tanks = new ArrayList<>();
    public List<Bullet> bullets = new ArrayList<>();
    public List<Explode> Explodes = new ArrayList<>();

    public Tank redTank = new Tank(100,100,Dir.DOWN, Group.RED, this);
    public Tank blueTank;

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

//        autoAddEnemy();

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
        g.drawString("敌方坦克的数量：" + tanks.size(), 10, 50);
        g.drawString("子弹的数量：" + bullets.size(), 10, 70);
        g.drawString("爆炸的数量：" + Explodes.size(), 10, 90);
        g.setColor(c);

        if(!redTank.isLiving()){
            c = g.getColor();
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER", CONSTANTS.WINDOW_WIDTH / 2 - 50, CONSTANTS.WINDOW_HEIGHT / 2 - 10);
            g.setColor(c);
            bullets.removeAll(bullets);
            tanks.removeAll(tanks);
            return;
        }

        redTank.paint(g);

        for(ListIterator<Explode> blastIterator = Explodes.listIterator(); blastIterator.hasNext();){
            Explode explode = blastIterator.next();
            explode.paint(g);
            if(!explode.isLiving()) blastIterator.remove();
        }

        for(ListIterator<Bullet> bulletIterator = bullets.listIterator();bulletIterator.hasNext();){
            Bullet bullet = bulletIterator.next();
            bullet.paint(g);
            if(!bullet.isLiving()) bulletIterator.remove();
        }

        for(ListIterator<Tank> tankListIterator = tanks.listIterator();tankListIterator.hasNext();){
            Tank tank = tankListIterator.next();
            tank.paint(g);
            if(!tank.isLiving()) tankListIterator.remove(); // ConcurrentModificationException   //异步新增tank,数量对不上，导致报错
        }

        for(ListIterator<Bullet> bulletIterator = bullets.listIterator();bulletIterator.hasNext();){
            Bullet bullet = bulletIterator.next();
            bullet.collisionWidth(redTank);
            for(ListIterator<Tank> tankListIterator = tanks.listIterator();tankListIterator.hasNext();){
                bullet.collisionWidth(tankListIterator.next());
            }

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
            setMainTankDir(redTank);
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
                    redTank.fire();
                default:
                    break;
            }
            setMainTankDir(redTank);
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
