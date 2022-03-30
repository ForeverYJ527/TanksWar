import javax.swing.*;

public class EnemyGreen extends Tanks {

    public EnemyGreen(int x, int y) {
        super(70, 70, x, y, 10);
    }

    //重写getImage
    public ImageIcon getImage() {
        switch (direction) {
            case UP:
                return Images.EnemyGreenUp;//返回敌人方向图片
            case DOWN:
                return Images.EnemyGreenDown;
            case LEFT:
                return Images.EnemyGreenLeft;
            default:
                return Images.EnemyGreenRight;
        }
    }

}
