
import java.awt.*;

public class Obstacle {
    int x;
    int y;
    int width = 70;
    int height = 70;
    boolean isLive;//判断所有障碍物是否存活
    Image obstacleImage;//枚举区分
    obstacle kind;

    public void setisLive(boolean b) {
    }

    public enum obstacle {
        grass, sea, wall, home;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setIs_Dead(boolean isLive) {
        this.isLive = isLive;
    }

    public obstacle getkind() {
        return kind;
    }

    public void setkind(obstacle kind) {
        this.kind = kind;
    }

    public Image getObstacleImage() {
        return obstacleImage;
    }

    public void setObstacleImage(Image obstacleImage) {
        this.obstacleImage = obstacleImage;
    }

    public Obstacle(int x, int y, obstacle kind) {
        super();
        this.x = x;
        this.y = y;
        this.kind = kind;
    }

    //绘制各种墙体
    public void draw() {
        switch (kind) {
            case grass:
                setObstacleImage(Toolkit.getDefaultToolkit().getImage("img/Glass.png"));
                break;
            case sea:
                setObstacleImage(Toolkit.getDefaultToolkit().getImage("img/Sea.png"));
                break;
            case wall:
                setObstacleImage(Toolkit.getDefaultToolkit().getImage("img/Wall.png"));
                break;
            case home:
                setObstacleImage(Toolkit.getDefaultToolkit().getImage("img/Home.png"));
                break;
        }
    }

    //墙体的矩形，为碰撞做准备
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }

}


