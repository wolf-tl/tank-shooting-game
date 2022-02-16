package com.tl.tank_battle3;

import java.util.Vector;

/**
 * @author tl
 */
public class EnemyTank extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
//    boolean islive = true;
    Vector<EnemyTank> enemyTanks=new Vector<>();

    public EnemyTank(int x, int y, int direct,boolean islife) {
        super(x, y, direct,islife);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public boolean hitEnemy(){
        switch (getDirect()){
            case 0://上
                for(int i=0;i<enemyTanks.size();i++) {//遍历所有的敌方坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //如果对方坦克是上下的
                    if ( enemyTank != this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {//排除自己进入判断
                            //判断是否进入对方坦克区域
                            if (this.getX() > enemyTank.getX() && this.getX() < enemyTank.getX() + 40
                                    && this.getY() > enemyTank.getY() && this.getY() < enemyTank.getY() + 60 ||
                                    this.getX() + 40 > enemyTank.getX() && this.getX() + 40 < enemyTank.getX() + 40
                                            && this.getY() > enemyTank.getY() && this.getY() < enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //如果对方坦克是左右的
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() > enemyTank.getX() && this.getX() < enemyTank.getX() + 60
                                    && this.getY() > enemyTank.getY() && this.getY() < enemyTank.getY() + 40 ||
                                    this.getX() + 40 > enemyTank.getX() && this.getX() + 40 < enemyTank.getX() + 60
                                            && this.getY() > enemyTank.getY() && this.getY() < enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1://右
                for(int i=0;i<enemyTanks.size();i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank!=this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() + 60 > enemyTank.getX() && this.getX() + 60 < enemyTank.getX() + 40
                                    && this.getY() > enemyTank.getY() && this.getY() < enemyTank.getY() + 60 ||
                                    this.getX() + 60 > enemyTank.getX() && this.getX() + 60 < enemyTank.getX() + 40
                                            && this.getY() + 40 > enemyTank.getY() && this.getY() + 40 < enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() + 60 > enemyTank.getX() && this.getX() + 60 < enemyTank.getX() + 60
                                    && this.getY() > enemyTank.getY() && this.getY() < enemyTank.getY() + 40 ||
                                    this.getX() + 60 > enemyTank.getX() && this.getX() + 60 < enemyTank.getX() + 60
                                            && this.getY() + 40 > enemyTank.getY() && this.getY() + 40 < enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2://下
                for(int i=0;i<enemyTanks.size();i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank!=this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() > enemyTank.getX() && this.getX() < enemyTank.getX() + 40
                                    && this.getY() + 60 > enemyTank.getY() && this.getY() + 60 < enemyTank.getY() + 60 ||
                                    this.getX() + 40 > enemyTank.getX() && this.getX() + 40 < enemyTank.getX() + 40
                                            && this.getY() + 60 > enemyTank.getY() && this.getY() + 60 < enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() > enemyTank.getX() && this.getX() < enemyTank.getX() + 60
                                    && this.getY() + 60 > enemyTank.getY() && this.getY() + 60 < enemyTank.getY() + 40 ||
                                    this.getX() + 40 > enemyTank.getX() && this.getX() + 40 < enemyTank.getX() + 60
                                            && this.getY() + 60 > enemyTank.getY() && this.getY() + 60 < enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3://左
                for(int i=0;i<enemyTanks.size();i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank!=this) {
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            if (this.getX() > enemyTank.getX() && this.getX() < enemyTank.getX() + 40
                                    && this.getY() > enemyTank.getY() && this.getY() < enemyTank.getY() + 60 ||
                                    this.getX() > enemyTank.getX() && this.getX() < enemyTank.getX() + 40
                                            && this.getY() + 40 > enemyTank.getY() && this.getY() + 40 < enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            if (this.getX() > enemyTank.getX() && this.getX() < enemyTank.getX() + 60
                                    && this.getY() > enemyTank.getY() && this.getY() < enemyTank.getY() + 40 ||
                                    this.getX() > enemyTank.getX() && this.getX() < enemyTank.getX() + 60
                                            && this.getY() + 40 > enemyTank.getY() && this.getY() + 40 < enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }
    @Override
    public void run() {
        Shot shot=null;
        while (true) {
            setDirect((int) (Math.random() * 4));
            switch (getDirect()) {
                case 0://上
                    for (int i = 0; i < 30; i++) {
                        if(!hitEnemy()) {
                            moveup();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1://右
                    for (int i = 0; i < 30; i++) {
                        if(!hitEnemy()) {
                            moveright();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2://下
                    for (int i = 0; i < 30; i++) {
                        if(!hitEnemy()) {
                            movedown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 3://左
                    for (int i = 0; i < 30; i++) {
                        if(!hitEnemy()) {
                            moveleft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            if(islife!=true){
                break;
            }
            //控制坦克发射子弹数量
            if(islife&&shots.size()<100){
                switch (getDirect()) {
                    case 0://上
                        shot = new Shot(getDirect(), getX() + 20, getY() , 5);

                    break;
                    case 1://右
                        shot = new Shot(getDirect(), getX() + 60, getY()+20 , 5);

                        break;
                    case 2://下
                        shot = new Shot(getDirect(), getX() + 20, getY()+60 , 5);

                        break;
                    case 3://左
                        shot = new Shot(getDirect(), getX(), getY()+20 , 5);

                        break;
                }
                shots.add(shot);
                Thread thread = new Thread(shot);
                thread.start();
            }
        }
    }
}




