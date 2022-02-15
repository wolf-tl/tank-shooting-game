package com.tl.tank_battle3;

/**
 * @author 唐亮
 */
public class Tank {
    private int x;
    private int y;
    private int direct;
    boolean islife = true;


    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int x, int y,int direct,boolean islife) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.islife=islife;
    }
    public void moveup(){
        if(y>0) {
            y-=3;
        }
    }
    public void movedown(){
        if(y<690) {
            y+=3;
        }
    }
    public void moveleft(){
        if(x>0) {
            x-=3;
        }
    }
    public void moveright(){
        if(x<940) {
            x+=3;
        }
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
