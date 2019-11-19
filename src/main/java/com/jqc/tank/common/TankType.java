package com.jqc.tank.common;

public enum TankType{
    PLAYER(CONSTANTS.TANK_SPEED_5), NORMAL(CONSTANTS.TANK_SPEED_5);

    int speed;
    TankType(int speed) {
        this.speed = speed;
    }
    public int getSpeed(){
        return this.speed;
    }
}
