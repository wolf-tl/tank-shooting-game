package com.tl.tank_battle3;

/**
 * @author tl
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

