package com.tl.tank_battle3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import static com.tl.tank_battle3.Recorder.*;

/**
 * @author tl
 */
public class MyPanel extends JPanel implements KeyListener ,Runnable{//创建面板
    MyTank myTank = null;//定义我的坦克
    EnemyTank enemyTank = null;//定义敌人的坦克
    Vector<EnemyTank> enemyTanks;
    Vector<Bomb> bombs= new Vector<>();
    Vector<Node_> node_s=null;
    Image image =null;
    Image image1 =null;
    Image image2 =null;

    public MyPanel(String key) {
        //为了加强代码的健壮性，需要判断目标文件是否存在
        File file=new File(Recorder.getPathname());
        if(!file.exists()){
            key="y";
        }
        myTank = new MyTank(100,600,0,true);//初始化我的坦克
        enemyTanks = new Vector<>();
        Recorder.setEnemyTanks(enemyTanks);//要放在enemyTanks初始化后
        int enemySize = 8;
        //通过两种初始化来选择继续上把游戏还是开始新游戏,使用key监听器监听用户键盘，
        //使用switch语句判断两种初始化

        //1.继续上把游戏  传入记录的信息，int x,y;int direction
        //文件中多行信息代表多个敌人坦克信息，因此需要创建一个有以上属性的对象
        //每行数据存入一个对象中，把所有对象装进Vector中，初始化时遍历Vector
        switch (key) {
            case "y":
            for (int i = 0; i < enemySize; i++)//初始化敌人坦克
            {
                //虽然EnemyTank的线程设置了方向，但是初始化的方向在shot中被优先执行
                enemyTank = new EnemyTank((100 + 100 * i), 0, 2, true);//添加三个不同enemyTank
                enemyTank.setEnemyTanks(enemyTanks);//传入的enemyTanks是动态更新的，因为始终是一个enemyTanks（相当于引用传递）

                Thread thread = new Thread(enemyTank);
                thread.start();
                Shot shot = new Shot(enemyTank.getDirect(), enemyTank.getX() + 20, enemyTank.getY() + 60, 5);
                new Thread(shot).start();
                enemyTank.shots.add(shot);//三个不同的enemyTank.shots.add()
                enemyTanks.add(enemyTank);
            }
            break;
            case "n":
                    Recorder.reader_();
                    node_s = Recorder.getNode_s();
                    for (int i = 0; i < node_s.size(); i++)//初始化敌人坦克
                    {
                        Node_ node_ = node_s.get(i);
                        enemyTank = new EnemyTank(node_.getX(), node_.getY(), node_.getDirection(), true);//添加三个不同enemyTank
                        enemyTank.setEnemyTanks(enemyTanks);//传入的enemyTanks是动态更新的，因为始终是一个enemyTanks（相当于引用传递）

                        Thread thread = new Thread(enemyTank);
                        thread.start();
                        Shot shot = new Shot(enemyTank.getDirect(), enemyTank.getX() + 20, enemyTank.getY() + 60, 5);
                        new Thread(shot).start();
                        enemyTank.shots.add(shot);//三个不同的enemyTank.shots.add()
                        enemyTanks.add(enemyTank);
                    }
                break;
        }

        //Toolkit.getDefaultToolkit()需要使用java.awt.Toolkit
        //此处的Tollkit可能导致paint()快速加载多次
        image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/原子弹1.png"));
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/原子弹2.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/原子弹3.png"));
    }
    @Override
    public void paint(Graphics g)
    {
//        System.out.println("开始绘制");
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0,0,1000,750);
        //绘制一块区域，用于显示战绩
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);//创建字体
        g.setFont(font);//设置字体
        g.drawString("累计击毁敌方坦克数量：",1000,50);//写字
        draw_tank(1010,100,g,0,1);
        g.setColor(Color.black);
        g.drawString(Recorder.getMyhitnums() + "",1060,100);
        //封装方法
        //绘制我方坦克
        if(myTank.islife&&myTank!=null) {
            draw_tank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0);
        }
        //绘制敌方坦克
            for (int i = 0; i < enemyTanks.size(); i++) {//普通遍历,遍历不同的enemyTank对象
                enemyTank = enemyTanks.get(i);
//                draw_tank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                if (enemyTank.islife) {
                    draw_tank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                }
                    //？？？在此处移除被销毁的坦克，旁边的坦克会闪动
//                else{//我猜想执行enemyTanks.remove(enemyTank)需要牵扯到相邻下标的元素，在短时间内无法完成操作
//                        enemyTanks.remove(enemyTank);
//                    }
                    //绘制敌方坦克发射子弹的轨迹
                    //当敌人坦克被击毁后，它的所有子弹都会消失，后期需要优化
                    for (int j = 0; j < enemyTank.shots.size(); j++) {//把某个坦克的所有子弹全部打出来
                        Shot shot = enemyTank.shots.get(j);
                        if (shot.islife) {
                            g.drawOval(shot.x, shot.y, 10, 10);
                        } else {
                            System.out.println("移除");//防止画板重复画处于边界处的子弹
                            enemyTank.shots.remove(shot);
                        }
                    }

            }

            //绘制爆炸效果
            for (int i=0;i<bombs.size();i++){
               Bomb bomb = bombs.get(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(bomb.life>6){
                   g.drawImage(image,bomb.x,bomb.y,60,60,this);
                   System.out.println("艺术就是爆炸！！！");
               }
               else if(bomb.life>3){
                   g.drawImage(image1,bomb.x,bomb.y,60,60,this);
                   System.out.println("砰砰砰！！！");
               }
               else {
                   g.drawImage(image2,bomb.x,bomb.y,60,60,this);
                   System.out.println("嘣嘣嘣！！！");
               }
               bomb.substract();
               if(bomb.life==0){
                   bombs.remove(bomb);
               }
            }
//        for(EnemyTank enemyTank:enemyTanks){//强制for循环遍历
//            draw_tank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
//
//            for(int j=0;j<enemyTank.shots.size();j++){
//
//            }
//        }
        //绘制我方坦克的炮弹轨迹
//        if(myTank.shot!=null&&myTank.shot.islife==true) {
//            g.drawOval(myTank.shot.x, myTank.shot.y, 10, 10);
//        }//这种方法只能绘制一个炮弹的轨迹因为只能获取一个Shot线程
        //通过Vector集合绘制多次发射炮弹的轨迹
        for(int i=0;i<myTank.shots.size();i++){
            Shot shot = myTank.shots.get(i);
            if(shot!=null&&shot.islife) {
            g.drawOval(shot.x, shot.y, 10, 10);
            }
            else {
                myTank.shots.remove(shot);
            }
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
            case 0://朝上
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1://朝右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.drawOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2://朝下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://朝左
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
    //判断我方子弹是否打中敌方坦克
    //引用类型的参数传递传递的是引用地址的拷贝，可以改变实际中对象的内容
    public void hitTank(Shot shot, Tank enemyTank){
        switch(enemyTank.getDirect()){
            case 0:
            case 2:
                if (shot.x>enemyTank.getX()&&shot.x<enemyTank.getX()+40
                &&shot.y>enemyTank.getY()&&shot.y<enemyTank.getY()+60){
                    shot.islife=false;
                    enemyTank.islife=false;
                    enemyTanks.remove(enemyTank);
                    Recorder.add();
                    Bomb bomb = new Bomb(enemyTank.getX(),enemyTank.getY());
                    System.out.println("进入爆炸区域！");
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (shot.x>enemyTank.getX()&&shot.x<enemyTank.getX()+60
                        &&shot.y>enemyTank.getY()&&shot.y<enemyTank.getY()+40){
                    shot.islife=false;
                    enemyTank.islife=false;
                    enemyTanks.remove(enemyTank);
                    Recorder.add();
                    Bomb bomb = new Bomb(enemyTank.getX(),enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
        }

    }
    //判断我方坦克是否被击中
    public void hitMyTank(Shot shot,Tank myTank){
        switch (myTank.getDirect()){
            case 0:
            case 2:
                if (shot.x>myTank.getX()&&shot.x<myTank.getX()+40
                        &&shot.y>myTank.getY()&&shot.y<myTank.getY()+60){
                    shot.islife=false;
                    myTank.islife=false;
                    Bomb bomb = new Bomb(myTank.getX(),myTank.getY());
                    System.out.println("进入爆炸区域！");
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (shot.x>myTank.getX()&&shot.x<myTank.getX()+60
                        &&shot.y>myTank.getY()&&shot.y<myTank.getY()+40){
                    shot.islife=false;
                    myTank.islife=false;
                    Bomb bomb = new Bomb(myTank.getX(),myTank.getY());
                    bombs.add(bomb);
                }
                break;
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
        //每次键盘输入J都会创建新Shot对象和新线程去执行Shot.run()方法
        if (e.getKeyCode()==KeyEvent.VK_J){
            myTank.shotEnemyTank();
            //当shot对象被回收时，shot!=null
//            if(myTank.shot==null||!myTank.shot.islife) {
//                myTank.shotEnemyTank();
//            }


        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断敌人坦克是否被击中
            for(int j=0;j<myTank.shots.size();j++) {//取出每颗子弹与敌人的每辆坦克进行比较
                Shot shot=myTank.shots.get(j);
                if (shot != null && shot.islife) {//判断我的子弹是否存活
                    for (int i = 0; i < enemyTanks.size(); i++) {
                        EnemyTank enemyTank = enemyTanks.get(i);
                        hitTank(shot, enemyTank);
                    }
                }
            }
            //判断我方坦克是否被击中
            //取出敌人坦克
            for(int i=0;i<enemyTanks.size();i++){
                EnemyTank enemyTank=enemyTanks.get(i);
                //取出敌人坦克的所有子弹
                for(int j=0;j<enemyTank.shots.size();j++){
                    Shot shot=enemyTank.shots.get(j);
                    if(shot!=null&&shot.islife){
                        hitMyTank(shot,myTank);
                    }
                }
            }
            repaint();
        }
    }
}
