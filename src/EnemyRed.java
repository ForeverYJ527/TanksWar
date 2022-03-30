import javax.swing.*;

public class EnemyRed extends Tanks {

    public EnemyRed(int x, int y) {
        super(70, 70, x, y, 10);
    }

    //重写getImage
    public ImageIcon getImage() {
        switch (direction) {
            case UP:
                return Images.EnemyRedUp;//返回敌人方向图片
            case DOWN:
                return Images.EnemyRedDown;
            case LEFT:
                return Images.EnemyRedLeft;
            default:
                return Images.EnemyRedRight;
        }
    }

}
