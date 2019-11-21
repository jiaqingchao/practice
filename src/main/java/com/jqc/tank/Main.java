package com.jqc.tank;

import com.jqc.tank.bean.Audio;
import com.jqc.tank.bean.Tank;
import com.jqc.tank.common.Dir;
import com.jqc.tank.common.Group;

public class Main {
    public static void main(String[] args) throws Exception{
        TankFrame tf = new TankFrame();

        for(int i = 0; i < 5; i++){
            tf.tanks.add(new Tank(50 + i * 80,200, Dir.DOWN, Group.AI, tf));
        }

        //new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
