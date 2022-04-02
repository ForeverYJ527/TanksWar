import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/*
回车开始游戏，方向键操作移动，空格发射子弹
*/

public class World extends JPanel {
    public static final int WIDTH = 700;//窗口的宽高
    public static final int HEIGHT = 700;

    public static final int START = -1;
    public static final int RUNNING = 0;
    public static final int GAME_OVER = 1;
    protected int state = START;//当前状态（默认为开始）

    int level = 0;//第几关
    Map[] map = {new Map1(), new Map2(), new Map3(), new Map4(), new Map5()};//地图

    private Player player = new Player();//玩家坦克
    private Tanks[] enemys = {new EnemyGreen(175 + 0, 20),
            new EnemyWhite(175 + 100, 20),
            new EnemyRed(175 + 200, 20),
            new EnemyYellow(175 + 300, 20)};

    private Bullet[] playerBullets = {};//玩家子弹
    private Bullet[] enemyBullets = {};//敌人子弹

    //子弹移动
    public void moveAction() {//每10毫秒走一次
        for (int i = 0; i < playerBullets.length; i++) {
            playerBullets[i].move();
            if (playerBullets[i].CollisionWall(map[level])) {
                boom(playerBullets[i].x - 25, playerBullets[i].y - 25);
                playerBullets[i].goDead();
                if (map[level].getObstacles().get(playerBullets[i].yj).getkind() == Obstacle.obstacle.wall) {
                    map[level].getObstacles().get(playerBullets[i].yj).isLive = true;
                    break;
                }
            }
        }
        for (int i = 0; i < enemyBullets.length; i++) {
            enemyBullets[i].move();
            if (enemyBullets[i].CollisionWall(map[level])) {
                boom(enemyBullets[i].x - 25, enemyBullets[i].y - 25);
                enemyBullets[i].goDead();
                if (map[level].getObstacles().get(enemyBullets[i].yj).getkind() == Obstacle.obstacle.wall) {
                    map[level].getObstacles().get(enemyBullets[i].yj).isLive = true;
                    break;
                }
                if (map[level].getObstacles().get(enemyBullets[i].yj).getkind() == Obstacle.obstacle.home) {
                    map[level].getObstacles().get(enemyBullets[i].yj).isLive = true;
                    player.life--;
                    //state = GAME_OVER;
                    break;
                }
            }
        }
    }

    //子弹，敌人越界删除处理
    public void outOfBoundsAction() {//每10毫秒走一次
        for (int i = 0; i < playerBullets.length; i++) {//遍历所有玩家子弹
            if (playerBullets[i].isOutBounds() || playerBullets[i].isDead()) {//若越界了或死了
                playerBullets[i] = playerBullets[playerBullets.length - 1];//修改越界元素为最后一个元素
                playerBullets = Arrays.copyOf(playerBullets, playerBullets.length - 1);
            }
        }
        for (int i = 0; i < enemyBullets.length; i++) {//遍历所有敌人子弹
            if (enemyBullets[i].isOutBounds() || enemyBullets[i].isDead()) {//若越界了或死了
                enemyBullets[i] = enemyBullets[enemyBullets.length - 1];//修改越界元素为最后一个元素
                enemyBullets = Arrays.copyOf(enemyBullets, enemyBullets.length - 1);
            }
        }
        for (int i = 0; i < enemys.length; i++) {//遍历所有敌人
            if (enemys[i].isOutBounds() || enemys[i].isDead()) {//若死了
                enemys[i] = enemys[enemys.length - 1];//修改越界元素为最后一个元素
                enemys = Arrays.copyOf(enemys, enemys.length - 1);
            }
        }
    }

    //坦克移动
    private int enemyMoveIndex = 0;

    public void enemyMoveAction() {
        enemyMoveIndex++;
        Random ran = new Random();
        if (enemyMoveIndex % 100 == 0) {//改变方向
            for (int i = 0; i < enemys.length; i++) {
                enemys[i].direction = (int) (2 + ran.nextInt(4));//2+0--2+3
            }
        }
        if (enemyMoveIndex % 10 == 0) {//移动
            for (int i = 0; i < enemys.length; i++) {
                if (enemys[i].direction == Tanks.UP) {
                    enemys[i].moveUp();
                    enemys[i].is_CollisionWall(map[level]);
                }
                if (enemys[i].direction == Tanks.DOWN) {
                    enemys[i].moveDown();
                    enemys[i].is_CollisionWall(map[level]);
                }
                if (enemys[i].direction == Tanks.LEFT) {
                    enemys[i].moveLeft();
                    enemys[i].is_CollisionWall(map[level]);
                }
                if (enemys[i].direction == Tanks.RIGHT) {
                    enemys[i].moveRight();
                    enemys[i].is_CollisionWall(map[level]);
                }
            }
        }
    }

    private int bulletsEnterIndex = 0;

    //敌人子弹入场
    public void bulletsEnterAction() {
        bulletsEnterIndex++;
        if (bulletsEnterIndex % 100 == 0) {//1秒(10*100)走一次
            for (int i = 0; i < enemys.length; i++) {
                Bullet obj = enemys[i].shootBullet();//获取子弹对象
                enemyBullets = Arrays.copyOf(enemyBullets, enemyBullets.length + 1);//扩容
                enemyBullets[enemyBullets.length - 1] = obj;//将obj添加到最后一个元素上
            }
        }
    }

    //子弹击中敌人处理
    public void enemyBangAction() {//每10毫秒走一次
        //玩家子弹打敌人
        for (int i = 0; i < playerBullets.length; i++) {//遍历所有子弹
            Bullet b = playerBullets[i];//获取每个子弹
            for (int j = 0; j < enemys.length; j++) {//遍历每个敌人
                Tanks t = enemys[j];//获取每个敌人
                if (b.isLive() && t.isLive() && b.isHit(t)) {//若都活着且撞上了
                    boom(t.x, t.y);
                    t.goDead();//敌人去世
                    b.goDead();//子弹去世
                }
            }
        }
        //敌人子弹撞敌人
        for (int i = 0; i < enemys.length; i++) {
            Tanks t = enemys[i];
            for (int j = 0; j < enemyBullets.length; j++) {
                Bullet b = enemyBullets[j];
                if (t.isLive() && b.isLive() && t.isHit(b)) {//若都活着且撞上了
                    b.goDead();//子弹去世
                }
            }
        }
    }

    //子弹击中英雄处理
    public void playerBangAction() {
        for (int i = 0; i < enemyBullets.length; i++) {//遍历所有子弹
            Bullet b = enemyBullets[i];//获取每个子弹
            if (b.isLive() && player.isLive() && player.isHit(b)) {
                boom(b.x - 25, b.y - 25);
                b.goDead();//子弹去世
                player.life--;
            }
        }
    }

    //子弹击中子弹处理
    public void bulletBangAction() {//每10毫秒走一次
        for (int i = 0; i < enemyBullets.length; i++) {//遍历所有子弹
            for (int j = 0; j < enemyBullets.length; j++) {//遍历每个敌人
                if (enemyBullets[i] != enemyBullets[j]) {
                    Bullet b1 = enemyBullets[i];//获取子弹1
                    Bullet b2 = enemyBullets[j];//获取子弹2
                    if (b1.isLive() && b2.isLive() && b1.isHit(b2)) {//若都活着且撞上了
                        boom(b1.x - 25, b1.y - 25);
                        b1.goDead();//子弹1去世
                        b2.goDead();//子弹2去世
                    }
                }
            }
            for (int j = 0; j < playerBullets.length; j++) {//遍历每个敌人
                Bullet b1 = enemyBullets[i];//获取子弹1
                Bullet b2 = playerBullets[j];//获取子弹2
                if (b1.isLive() && b2.isLive() && b1.isHit(b2)) {//若都活着且撞上了
                    boom(b1.x - 25, b1.y - 25);
                    b1.goDead();//子弹1去世
                    b2.goDead();//子弹2去世
                }
            }
        }
    }

    //坦克撞坦克处理
    public void TankBangAction() {//每10毫秒走一次
        for (int i = 0; i < enemys.length; i++) {//遍历所有坦克
            Tanks t1 = enemys[i];//获取敌人1
            if (t1.isLive() && player.isLive() && t1.isHit(player)) {//若都活着且撞上了
                boom(t1.x, t1.y);
                t1.goDead();//敌人1去世
                player.life--;
            }
        }
        for (int i = 0; i < enemys.length; i++) {//遍历所有坦克
            Tanks t1 = enemys[i];//获取敌人1
            for (int j = 0; j < enemys.length; j++) {
                if (enemys[i] != enemys[j]) {
                    enemys[i].is_Hit(enemys[j]);
                    enemys[j].is_Hit(enemys[i]);
                }
            }
        }
    }

    private int time = 0;

    //敌人死亡爆炸
    public void boom(int x, int y) {
        time = 0;
        while (time < 100) {
            Images.Boom.paintIcon(null, getGraphics(), x, y);
            time++;
        }
    }

    private boolean b = true;
    private boolean stop;

    public void born() {
        stop = false;
        while (b) {
            time = 0;
            while (time < 666) {
                Images.Born1.paintIcon(null, getGraphics(), player.x, player.y);
                Images.Born1.paintIcon(null, getGraphics(), 175, 20);
                Images.Born1.paintIcon(null, getGraphics(), 275, 20);
                Images.Born1.paintIcon(null, getGraphics(), 375, 20);
                Images.Born1.paintIcon(null, getGraphics(), 475, 20);
                time++;
            }
            time = 0;
            while (time < 666) {
                Images.Born2.paintIcon(null, getGraphics(), player.x, player.y);
                Images.Born2.paintIcon(null, getGraphics(), 175, 20);
                Images.Born2.paintIcon(null, getGraphics(), 275, 20);
                Images.Born2.paintIcon(null, getGraphics(), 375, 20);
                Images.Born2.paintIcon(null, getGraphics(), 475, 20);
                time++;
            }
            time = 0;
            while (time < 666) {
                Images.Born3.paintIcon(null, getGraphics(), player.x, player.y);
                Images.Born3.paintIcon(null, getGraphics(), 175, 20);
                Images.Born3.paintIcon(null, getGraphics(), 275, 20);
                Images.Born3.paintIcon(null, getGraphics(), 375, 20);
                Images.Born3.paintIcon(null, getGraphics(), 475, 20);
                time++;
            }
            time = 0;
            while (time < 666) {
                Images.Born4.paintIcon(null, getGraphics(), player.x, player.y);
                Images.Born4.paintIcon(null, getGraphics(), 175, 20);
                Images.Born4.paintIcon(null, getGraphics(), 275, 20);
                Images.Born4.paintIcon(null, getGraphics(), 375, 20);
                Images.Born4.paintIcon(null, getGraphics(), 475, 20);
                time++;
            }
            b = false;
        }
        stop = true;
    }

    private int index = 0;
    private boolean isready = false;

    public void isReady() {
        index++;
        if (index % 100 == 0) {
            isready = true;
        }
    }

    public void isGameOver() {
        if (player.isPlayDead())
            state = GAME_OVER;
    }

    public void levelUp() {
        if (enemys.length <= 0) {
            for (int i = 0; i < playerBullets.length; i++) {
                playerBullets[i].goDead();
            }
            for (int i = 0; i < enemyBullets.length; i++) {
                enemyBullets[i].goDead();
            }
            b = true;
            player.x = 0;
            player.y = 560;
            born();
            Tanks[] t = {new EnemyGreen(175 + 0, 20),
                    new EnemyWhite(175 + 100, 20),
                    new EnemyRed(175 + 200, 20),
                    new EnemyYellow(175 + 300, 20)};
            enemys = t;
            level++;
            if (level > 4) {
                level = 0;
                Map[] reMap = {new Map1(), new Map2(), new Map3(), new Map4(), new Map5()};
                map = reMap;
            }
        }
    }

    public void action() {
        KeyAdapter k = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {//键盘按键抬起触发
                if (state == START) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        state = RUNNING;
                    }
                }
                if (state == RUNNING && stop) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            player.moveUp();
                            player.is_CollisionWall(map[level]);
                            break;
                        case KeyEvent.VK_DOWN:
                            player.moveDown();
                            player.is_CollisionWall(map[level]);
                            break;
                        case KeyEvent.VK_LEFT:
                            player.moveLeft();
                            player.is_CollisionWall(map[level]);
                            break;
                        case KeyEvent.VK_RIGHT:
                            player.moveRight();
                            player.is_CollisionWall(map[level]);
                            break;
                        case KeyEvent.VK_SPACE:
                            if (isready) {
                                isready = false;
                                Bullet obj = player.shootBullet();//获取子弹对象
                                playerBullets = Arrays.copyOf(playerBullets, playerBullets.length + 1);//扩容
                                playerBullets[playerBullets.length - 1] = obj;
                            }
                    }
                }
            }
        };//键盘适配器
        addKeyListener(k);//添加侦听

        java.util.Timer timer = new Timer();//定时器
        int interval = 10;//定时间隔（以毫秒为单位）
        timer.schedule(new TimerTask() {//匿名内部类*****
            public void run() {//定时干的事（每10毫秒自动执行）
                repaint();//重画
                if (state == RUNNING) {
                    moveAction();//子弹移动
                    outOfBoundsAction();//子弹，敌人越界删除处理
                    enemyMoveAction();//坦克移动
                    bulletsEnterAction();//敌人子弹入场
                    enemyBangAction();//子弹击中敌人处理
                    playerBangAction();//子弹击中英雄处理
                    bulletBangAction();//子弹击中子弹处理
                    TankBangAction();//坦克撞坦克处理
                    born();
                    isReady();
                    isGameOver();
                    levelUp();
                }
            }
        }, interval, interval);//定时计划表
    }

    public void paint(Graphics g) {
        if (state == START) {
            Images.Start.paintIcon(null, g, 0, 0);//画背景图

            Font f1 = new Font("回车开始游戏", Font.BOLD, 40);
            g.setFont(f1);
            g.setColor(Color.white);
            g.drawString("回车开始游戏", 225, 325);

            Font f2 = new Font("方向键移动空格开炮", Font.BOLD, 40);
            g.setFont(f2);
            g.setColor(Color.red);
            g.drawString("方向键移动空格开炮", 175, 380);
        }
        if (state == RUNNING) {
            Images.BackGround.paintIcon(null, g, 0, 0);//画背景图

            map[level].draw(g);

            player.painImage(g);//画玩家坦克
            for (int i = 0; i < enemys.length; i++) {//画敌人坦克
                enemys[i].painImage(g);
            }
            for (int i = 0; i < playerBullets.length; i++) {//画玩家子弹
                playerBullets[i].painImage(g);
            }
            for (int i = 0; i < enemyBullets.length; i++) {//画敌人子弹
                enemyBullets[i].painImage(g);
            }

            map[level].drawGrass(g);

            Images.Life.paintIcon(null, g, 5, 5);
            Font f3 = new Font("*", Font.BOLD, 60);
            g.setFont(f3);
            g.setColor(Color.white);
            g.drawString(String.valueOf(player.life - 1), 70, 70);
        }
        if (state == GAME_OVER) {
            Images.Over.paintIcon(null, g, 230, 230);//画背景图
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();//3.
        World world = new World();
        world.setFocusable(true);
        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH + 16, HEIGHT + 39);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);//1设置窗口可见性，2尽快调用paint
        world.action();//启动程序运行
    }

}
