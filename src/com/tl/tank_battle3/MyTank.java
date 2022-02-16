package com.tl.tank_battle3;

import java.util.Vector;

/**
 * @author tl
 */
public class MyTank extends Tank {
    Shot shot=null;
    Vector<Shot> shots = new Vector<>();
    int speed=5;
    public MyTank(int x, int y,int direct,boolean islife) {
        super(x, y, direct,islife);
    }
    public void shotEnemyTank(){
        // 禁止同时发射5枚子弹
        if(shots.size()>5){
            return;
        }
        switch(getDirect()){
            case 0:
                shot=new Shot(getDirect(), getX() + 20, getY(), speed);
                break;
            case 1:
                shot=new Shot(getDirect(),getX()+60,getY()+20,speed);
                break;
            case 2:
                shot=new Shot(getDirect(),getX()+20,getY()+60,speed);
                break;
            case 3:
                shot=new Shot(getDirect(),getX(),getY()+20,speed);
                break;
        }

            shots.add(shot);
            Thread thread = new Thread(shot);
            thread.start();


    }
}
