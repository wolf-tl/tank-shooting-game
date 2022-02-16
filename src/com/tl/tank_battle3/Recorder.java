package com.tl.tank_battle3;


import java.io.*;
import java.util.Vector;

/**
 * @author tl
 */
//用于保存游戏记录，以及读取上局记录
public class Recorder {
    private static BufferedReader bufferedReader=null;
    private static BufferedWriter bufferedWriter=null;
    private static String pathname="F:\\我的战绩.txt";
    private static int myhitnums=0;
    private static Vector<EnemyTank> enemyTanks=null;
    private static Vector<Node_> node_s=new Vector<>();
//静态方法中只能使用静态成员
    //而静态成员可以在普通方法中使用
    public static int getMyhitnums() {
        return myhitnums;
    }

    public static String getPathname() {
        return pathname;
    }

    public static Vector<Node_> getNode_s() {
        return node_s;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks=enemyTanks;
    }

    public static void setMyhitnums(int myhitnums) {
        Recorder.myhitnums = myhitnums;
    }
    public static void add(){
        myhitnums++;
    }
    public static void writer_(){
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(pathname));
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathname));
            bufferedWriter.write(myhitnums+"\r\n");//bufferedWriter.write()需要传入字符串
//            bufferedWriter.write(myhitnums);//这样会乱码
            for(int i=0;i<enemyTanks.size();i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.islife) {
                    bufferedWriter.write(enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect()+"\r\n");
                }
            }
        }
         catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void reader_(){
        //读取文件信息，并分割提取x,y,direction

        try {
            bufferedReader = new BufferedReader(new FileReader(pathname));
            //使用Integer.parseInt()将字符串转为整型
            myhitnums=Integer.parseInt(bufferedReader.readLine());
            String file=null;
            while((file=bufferedReader.readLine())!=null){
                String str[]=file.split(" ");
                Node_ node_ = new Node_();
                node_.setX(Integer.parseInt(str[0]));
                node_.setY(Integer.parseInt(str[1]));
                node_.setDirection(Integer.parseInt(str[2]));
                node_s.add(node_);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
