import javax.swing.*;
import java.awt.*;

public class Player extends Tanks {
    int life;

    public Player() {
        super(70, 70, 0, 560, 5);
        life = 200;
    }

    //重写getImage
    public ImageIcon getImage() {
        switch (direction) {
            case UP:
                return Images.PlayerUp;//返回玩家方向图片
            case DOWN:
                return Images.PlayerDown;
            case LEFT:
                return Images.PlayerLeft;
            default:
                return Images.PlayerRight;
        }
    }

    public boolean isPlayDead() {
        return life <= 0;
    }

}
