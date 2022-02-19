# tank-shooting-game

#### 介绍
这是一个很适合新手学习的小项目，其中用到了多线程，面向对象思想，监控事件，GUI，集合，继承，接口等等基础知识；随着框架的学习，我会升级该项目，包括：设计更多关卡，更多元素，完善图形界面；如果有兴趣的小伙伴可以给颗星星，并加入我们吧~

#### 软件架构
软件架构说明:![多线程编程](https://images.gitee.com/uploads/images/2022/0219/194331_e0ce3bdc_10434854.png "屏幕截图.png")

#### 关键技术
面向对象思想： 每种元素都是一个类，包括我方坦克类，敌方坦克类，子弹类，爆炸效果类，保存游戏类等；
继承与多态以及接口：继承坦克类，继承画框类，继承画布类，实现键盘监听器接口；
多线程编程：子弹的直线移动、坦克的无规则移动、刷新画布；
集合：使用大量集合来保存或移除多个对象，比如一个坦克发射多颗子弹，当子弹销毁时移出集合；
监听器：用于监听键盘输入以及关闭游戏动作；
IO流：用于游戏存盘；
多种语句复合使用：switch, if, for, 增强for循环等；
核心点：该项目通过元素的方向和坐标来判断是否击中目标，子弹从何处发射，在何处销毁，并控制各个坦克之间不干涉，敌方坦克的自由移动，元素不能越过窗口

#### 未来版本
未来版本将会实现闯关游戏，通过升级，可以获得强大的技能，并能够换成强大的银河战舰，随着关卡难度增加，敌方元素增多，不止于坦克类型，还有各种形状的怪兽；地域也会随着关卡而改变，比如水上世界，天空领域；宇宙漫步等；
未来版本还会优化图形界面，将会结合前端来升级；

其实整体框架已经搭建好了，未来版本升级并不是难事，随着我对框架的学习，后期将会实现这个未来版本，

很期待有感兴趣的伙伴，可以一起学习交流~
