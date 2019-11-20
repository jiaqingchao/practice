package com.jqc.tank.bean;

import com.jqc.tank.TankFrame;
import com.jqc.tank.common.ResourceMgr;

import java.awt.*;

public class Explode {
    private int x;
    private int y;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int index = 0;
    private boolean living = true;

    private TankFrame tf;


    public Explode(int x, int y,TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Audio("audio/explode.wav").run();
    }

    public boolean isLiving() {
        return living;
    }

    public void paint(Graphics g) {
        if(!isLiving()){
            return;
        }
        g.drawImage(ResourceMgr.explodes[index++], this.x, this.y,null);
        if(index >= ResourceMgr.explodes.length){
            living = false;
        }
    }
}
