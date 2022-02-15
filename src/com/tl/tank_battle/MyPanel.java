package com.tl.tank_battle;

import javax.swing.*;
import java.awt.*;

/**
 * @author 唐亮
 */
public class MyPanel extends JPanel {//创建面板
    MyTank myTank = null;//定义我的坦克

    public MyPanel() {
        myTank = new MyTank(100,100);//初始化我的坦克
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0,0,1000,750);
        //封装方法
        draw_tank(myTank.getX(),myTank.getY(),g,0,0);
    }

    /**
     *
     * @param x 坦克的横坐标
     * @param y 坦克的纵坐标
     * @param g 画笔
     * @param direct 坦克方向（上下左右）
     * @param type 我方坦克与敌方坦克
     */
    public void draw_tank(int x,int y,Graphics g,int direct,int type)
    {
        switch ( type) {
            case 0://我方坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌方坦克
                g.setColor(Color.orange);
                break;
        }
        switch (direct){
            case 0:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y-10);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }


}
