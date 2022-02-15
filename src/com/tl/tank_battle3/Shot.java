package com.tl.tank_battle3;

/**
 * @author 唐亮
 */
//用于创建子弹对象和进程
public class Shot implements Runnable{

    int direct;
    int x;
    int y;
    int speed;
    boolean islife=true;


    public Shot(int direct, int x, int y,int speed) {
        this.direct = direct;
        this.x = x;
        this.y = y;
        this.speed=speed;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch(direct){
                case 0://朝上
                    y-=speed;
                    break;
                case 1://朝右
                    x+=speed;
                    break;
                case 2://朝下
                    y+=speed;
                    break;
                case 3://朝左
                    x-=speed;
                    break;
            }
//            System.out.println("子弹 x= "+ x +"  y= "+y);
            if (!(x>=0&&x<=1000&&y>=0&&y<=750&&islife)){
                islife=false;
                System.out.println("子弹销毁");
                break;
            }

        }
    }
}
