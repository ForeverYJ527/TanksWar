import com.sun.deploy.net.socket.UnixDomainSocket;

import javax.swing.*;
import java.awt.*;

public abstract class Tanks {
    public static final int LIVE = 0;//活着
    public static final int DEAD = 1;//死了
    protected int state = LIVE;//当前状态（默认为活着）

    public static final int UP = 2;//上
    public static final int DOWN = 3;//下
    public static final int LEFT = 4;//左
    public static final int RIGHT = 5;//右
    protected int direction = UP;//当前状态（默认为向上）

    protected int width;//宽
    protected int height;//高
    protected int x;//x坐标
    protected int y;//y坐标
    protected int speed;//速度
    protected int yj;

    public Tanks(int width, int height, int x, int y, int speed) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void moveUp() {
        if (y >= 0 + 0) {
            y -= speed;
            direction = UP;
        }
    }

    public void moveDown() {
        if (y <= 700 - 70 - 0) {
            y += speed;
            direction = DOWN;
        }

    }

    public void moveLeft() {
        if (x >= 0 + 0) {
            x -= speed;
            direction = LEFT;
        }

    }

    public void moveRight() {
        if (x <= 700 - 70 - 0) {
            x += speed;
            direction = RIGHT;
        }

    }

    //发射炸弹
    public Bullet shootBullet() {
        switch (direction) {
            case UP:
                return new Bullet(x + 35 - 10, y - 25, direction);//子弹的初始坐标和玩家坐标+玩家方向有关
            case DOWN:
                return new Bullet(x + 35 - 10, y + 75, direction);
            case LEFT:
                return new Bullet(x - 25, y + 35 - 10, direction);
            default:
                return new Bullet(x + 75, y + 35 - 10, direction);
        }
    }

    //获取对象的图片
    public abstract ImageIcon getImage();

    public boolean isLive() {
        return state == LIVE;//true表示活着，false表示死了
    }

    public boolean isDead() {
        return state == DEAD;
    }

    public void painImage(Graphics g) {
        getImage().paintIcon(null, g, x, y);
    }

    //碰撞检测this：指一个对象 other：另一个对象
    public boolean isHit(Tanks other) {
        //假设this指玩家，other指子弹
        int x1 = this.x - other.width;//x1：玩家的x-子弹的宽
        int x2 = this.x + this.width;//x2：玩家的x+玩家的宽
        int y1 = this.y - other.height;//y1：玩家的y-子弹的高
        int y2 = this.y + this.height;//y2：玩家的y+玩家的高

        int x = other.x;//炮弹的x
        int y = other.y;//炮弹的y

        boolean isHit = false;
        if (x >= x1 && x <= x2 && y >= y1 && y <= y2)
            isHit = true;
        return isHit;
    }

    public void goDead() {
        state = DEAD;
    }

    //判断坦克和墙体的碰撞
    public boolean is_CollisionWall(Map wall) {
        for (int i = 0; i < wall.getObstacles().size(); i++) {
            if (new Rectangle(x, y, width, height).intersects(wall.getObstacles().get(i).getRec())) {
                if(wall.getObstacles().get(i).getkind() == Obstacle.obstacle.grass)
                    return false;
                switch (direction) {
                    case UP:
                        y += speed;
                        break;
                    case DOWN:
                        y -= speed;
                        break;
                    case LEFT:
                        x += speed;
                        break;
                    case RIGHT:
                        x -= speed;
                }
                yj=i;
                return true;
            }
        }
        return false;
    }

    //判断坦克和墙体的碰撞
    public boolean CollisionWall(Map wall) {
        for (int i = 0; i < wall.getObstacles().size(); i++) {
            if (new Rectangle(x, y, width, height).intersects(wall.getObstacles().get(i).getRec())) {
                if(wall.getObstacles().get(i).getkind() == Obstacle.obstacle.grass||wall.getObstacles().get(i).getkind() == Obstacle.obstacle.sea)
                    return false;
                switch (direction) {
                    case UP:
                        y += speed;
                        break;
                    case DOWN:
                        y -= speed;
                        break;
                    case LEFT:
                        x += speed;
                        break;
                    case RIGHT:
                        x -= speed;
                }
                yj=i;
                return true;
            }
        }
        return false;
    }

}
