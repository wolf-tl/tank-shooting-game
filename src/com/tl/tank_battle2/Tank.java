package com.tl.tank_battle2;

/**
 * @author tl
 */
public class Tank {
    private int x;
    private int y;
    private int direct;


    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y,int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
    public void moveup(){
        y--;
    }
    public void movedown(){
        y++;
    }
    public void moveleft(){
        x--;
    }
    public void moveright(){
        x++;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
