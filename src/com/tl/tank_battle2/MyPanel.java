package com.tl.tank_battle2;

import jdk.nashorn.internal.ir.CallNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author tl
 */
public class MyPanel extends JPanel implements KeyListener {//创建面板
    MyTank myTank = null;//定义我的坦克
    EnemyTank enemyTank = null;//定义敌人的坦克
    Vector<EnemyTank> enemyTanks;
    public MyPanel() {
        myTank = new MyTank(100,100,0);//初始化我的坦克
        enemyTanks = new Vector<>();
        int enemySize = 3;
        for(int i=0;i<enemySize;i++)//初始化敌人坦克
        {
            enemyTank = new EnemyTank((100+100*i),0,2);
            enemyTanks.add(enemyTank);
        }
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0,0,1000,750);
        //封装方法
        draw_tank(myTank.getX(),myTank.getY(),g,myTank.getDirect(),0);
//        for(int i=0;i<enemyTanks.size();i++){//普通遍历
//            EnemyTank enemyTank = enemyTanks.get(i);
//            draw_tank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
//        }
        for(EnemyTank enemyTank:enemyTanks){//强制for循环遍历
            draw_tank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
        }

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
                g.setColor(Color.pink);
                break;
        }
        switch (direct){
            case 0:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.drawOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.drawOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_W){
            myTank.setDirect(0);
            myTank.moveup();
        }

        else if (e.getKeyCode()==KeyEvent.VK_A)
        {
            myTank.setDirect(3);
            myTank.moveleft();
        }
        else if (e.getKeyCode()==KeyEvent.VK_D){
            myTank.setDirect(1);
            myTank.moveright();

        }
        else if (e.getKeyCode()==KeyEvent.VK_S){
            myTank.setDirect(2);
            myTank.movedown();

        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
