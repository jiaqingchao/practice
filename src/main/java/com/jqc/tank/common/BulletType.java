package com.jqc.tank.common;

public enum BulletType {

    PLAYER(CONSTANTS.BULLET_SPEED_10, CONSTANTS.BULLET_SIZE_5, CONSTANTS.BULLET_POWER_1),
    NORMAL(CONSTANTS.BULLET_SPEED_10, CONSTANTS.BULLET_SIZE_5, CONSTANTS.BULLET_POWER_1);

    int speed;
    int size;
    int power;
    BulletType(int speed, int size, int power) {
        this.speed = speed;
        this.size = size;
        this.power = power;
    }
    public int getSpeed(){
        return this.speed;
    }
    public int getSize(){
       return this.size;
    }
    public int getPower(){
        return this.power;
    }
}
