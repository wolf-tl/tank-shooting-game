package com.tl.tank_battle3;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Scanner;

/**
 * @author tl
 */
/*
debug:当坦克的方向为左右时，坦克盖是空心的：原因不明，可能是电脑显卡质量低
      我方坦克击毁后仍然隐形存在：
 */
public class TlTankGame_03 extends JFrame {
    MyPanel myPanel = null;
    public TlTankGame_03() {
        System.out.println("开始新游戏请输入y   继续上把游戏请输入n");
        Scanner scanner = new Scanner(System.in);
        String key=scanner.next();
        myPanel = new MyPanel(key);
        this.setVisible(true);//画框可视化
        Thread thread = new Thread(myPanel);
        thread.start();//启动线程，执行repaint()
        this.add(myPanel);
        //相同的尺寸，画框的大小和画板的大小不一样
        this.setSize(1300, 800);//定义画框大小
        this.addKeyListener(myPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
        //创建一个窗口关闭事件的监听器
        this.addWindowListener(new WindowAdapter() {//匿名内部类
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.writer_();
                System.out.println("窗口已关闭");
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        TlTankGame_03 tlTankGame_03 = new TlTankGame_03();
    }
}
