package com.tl.tank_battle2;

import javax.swing.*;

/**
 * @author tl
 */
public class TlTankGame_02 extends JFrame {
    MyPanel myPanel = null;

    public TlTankGame_02()  {
        myPanel = new MyPanel();
        this.add(myPanel);
        this.setSize(1000,750);//定义画框大小
        this.addKeyListener(myPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//画框可视化
    }

    public static void main(String[] args) {
        TlTankGame_02 tlTankGame_02 = new TlTankGame_02();
    }
}
