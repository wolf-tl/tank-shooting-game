package com.tl.tank_battle;

import javax.swing.*;
import java.awt.*;

/**
 * @author tl
 */
public class TlTankGame_01 extends JFrame {
    MyPanel myPanel = null;

    public TlTankGame_01()  {
        myPanel = new MyPanel();
        this.add(myPanel);
        this.setSize(1000,750);//定义画框大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//画框可视化
    }

    public static void main(String[] args) {
        TlTankGame_01 tlTankGame_01 = new TlTankGame_01();
    }
}
