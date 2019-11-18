package com.jqc.practice.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    int x = 200,y = 200;

    public TankFrame() throws HeadlessException {
        super();
        setVisible(true);
        setSize(800,600);
        setTitle("tank war");
        setResizable(false);

        addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

    }

    @Override
    public void paint(Graphics g){
        g.fillRect(x, y, 50, 50);
//        x+=10;
//        y+=10;
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
            moveTank();
        }
        public void moveTank(){
            // 根据四个boolean值，计算坦克方向，根据坦克方向和速度，自动移动位置。（假设坦克不能停）
            new Thread(()-> {
                while(bL){
                    try {
                        Thread.sleep(50);
                        x -= 10;
                    }catch (InterruptedException e){
                        e.getStackTrace();
                    }
                }
            }).start();
            new Thread(()-> {
                while(bU){
                    try {
                        Thread.sleep(50);
                        y -= 10;
                    }catch (InterruptedException e){
                        e.getStackTrace();
                    }
                }
            }).start();
            new Thread(()-> {
                while(bR){
                    try {
                        Thread.sleep(50);
                        x += 10;
                    }catch (InterruptedException e){
                        e.getStackTrace();
                    }
                }
            }).start();
            new Thread(()-> {
                while(bD){
                    try {
                        Thread.sleep(50);
                        y += 10;
                    }catch (InterruptedException e){
                        e.getStackTrace();
                    }
                }
            }).start();
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
                default:
                    break;
            }
        }
    }

}
