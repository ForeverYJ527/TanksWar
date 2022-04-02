import javax.swing.*;

public class Bullet extends Tanks {///子弹

    public Bullet(int x, int y, int direction) {//每个子弹的x和y和方向是不同的
        super(20, 20, x, y, 2);
        super.direction = direction;
    }

    public void move() {
        if (direction == UP)
            y -= speed;
        if (direction == DOWN)
            y += speed;
        if (direction == LEFT)
            x -= speed;
        if (direction == RIGHT)
            x += speed;
    }

    //重写getImage
    public ImageIcon getImage() {
        switch (direction) {
            case UP:
                return Images.BulletUp;//返回子弹方向图片
            case DOWN:
                return Images.BulletDown;
            case LEFT:
                return Images.BulletLeft;
            default:
                return Images.BulletRight;
        }
    }
}