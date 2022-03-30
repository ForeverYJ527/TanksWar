import javax.swing.*;

public class EnemyYellow extends Tanks {

    public EnemyYellow(int x, int y) {
        super(70, 70, x, y, 10);
    }

    //重写getImage
    public ImageIcon getImage() {
        switch (direction) {
            case UP:
                return Images.EnemyYellowUp;//返回敌人方向图片
            case DOWN:
                return Images.EnemyYellowDown;
            case LEFT:
                return Images.EnemyYellowLeft;
            default:
                return Images.EnemyYellowRight;
        }
    }

}
