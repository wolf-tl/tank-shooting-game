package com.tl.tank_battle3;

/**
 * @author 唐亮
 */
public class Bomb {
    int x;
    int y;
    boolean islife = true;
    int life = 9;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void substract(){

            life--;

    }
}

