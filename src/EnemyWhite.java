import javax.swing.*;

public class EnemyWhite extends Tanks {

    public EnemyWhite(int x, int y) {
        super(70, 70, x, y, 10);
    }

    //重写getImage
    public ImageIcon getImage() {
        switch (direction) {
            case UP:
                return Images.EnemyWhiteUp;//返回敌人方向图片
            case DOWN:
                return Images.EnemyWhiteDown;
            case LEFT:
                return Images.EnemyWhiteLeft;
            default:
                return Images.EnemyWhiteRight;
        }
    }

}
