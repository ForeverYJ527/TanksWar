import javax.swing.*;

public class Images {

    public static ImageIcon PlayerUp;//玩家图片
    public static ImageIcon PlayerDown;
    public static ImageIcon PlayerLeft;
    public static ImageIcon PlayerRight;

    public static ImageIcon BulletUp;//子弹图片
    public static ImageIcon BulletDown;
    public static ImageIcon BulletLeft;
    public static ImageIcon BulletRight;

    public static ImageIcon EnemyGreenUp;//敌人绿
    public static ImageIcon EnemyGreenDown;
    public static ImageIcon EnemyGreenLeft;
    public static ImageIcon EnemyGreenRight;

    public static ImageIcon EnemyRedUp;//敌人红
    public static ImageIcon EnemyRedDown;
    public static ImageIcon EnemyRedLeft;
    public static ImageIcon EnemyRedRight;

    public static ImageIcon EnemyWhiteUp;//敌人白
    public static ImageIcon EnemyWhiteDown;
    public static ImageIcon EnemyWhiteLeft;
    public static ImageIcon EnemyWhiteRight;

    public static ImageIcon EnemyYellowUp;//敌人黄
    public static ImageIcon EnemyYellowDown;
    public static ImageIcon EnemyYellowLeft;
    public static ImageIcon EnemyYellowRight;

    public static ImageIcon Born1;
    public static ImageIcon Born2;
    public static ImageIcon Born3;
    public static ImageIcon Born4;

    public static ImageIcon Boom;

    public static ImageIcon Start;
    public static ImageIcon Life;
    public static ImageIcon Over;

    public static ImageIcon BackGround;//背景图

    static {//初始化静态图片
        PlayerUp = new ImageIcon("img/PlayerUp.png");
        PlayerDown = new ImageIcon("img/PlayerDown.png");
        PlayerLeft = new ImageIcon("img/PlayerLeft.png");
        PlayerRight = new ImageIcon("img/PlayerRight.png");

        BulletUp = new ImageIcon("img/BulletUp.png");
        BulletDown = new ImageIcon("img/BulletDown.png");
        BulletLeft = new ImageIcon("img/BulletLeft.png");
        BulletRight = new ImageIcon("img/BulletRight.png");

        EnemyGreenUp = new ImageIcon("img/EnemyGreenUp.png");
        EnemyGreenDown = new ImageIcon("img/EnemyGreenDown.png");
        EnemyGreenLeft = new ImageIcon("img/EnemyGreenLeft.png");
        EnemyGreenRight = new ImageIcon("img/EnemyGreenRight.png");

        EnemyWhiteUp = new ImageIcon("img/EnemyWhiteUp.png");
        EnemyWhiteDown = new ImageIcon("img/EnemyWhiteDown.png");
        EnemyWhiteLeft = new ImageIcon("img/EnemyWhiteLeft.png");
        EnemyWhiteRight = new ImageIcon("img/EnemyWhiteRight.png");

        EnemyRedUp = new ImageIcon("img/EnemyRedUp.png");
        EnemyRedDown = new ImageIcon("img/EnemyRedDown.png");
        EnemyRedLeft = new ImageIcon("img/EnemyRedLeft.png");
        EnemyRedRight = new ImageIcon("img/EnemyRedRight.png");

        EnemyYellowUp = new ImageIcon("img/EnemyYellowUp.png");
        EnemyYellowDown = new ImageIcon("img/EnemyYellowDown.png");
        EnemyYellowLeft = new ImageIcon("img/EnemyYellowLeft.png");
        EnemyYellowRight = new ImageIcon("img/EnemyYellowRight.png");

        Born1 = new ImageIcon("img/Born1.png");
        Born2 = new ImageIcon("img/Born2.png");
        Born3 = new ImageIcon("img/Born3.png");
        Born4 = new ImageIcon("img/Born4.png");

        Boom = new ImageIcon("img/Boom.png");

        Start = new ImageIcon("img/Start.png");
        Life=new ImageIcon("img/Life.png");
        Over = new ImageIcon("img/Over.png");

        BackGround = new ImageIcon("img/BackGround.png");
    }

    public static void main(String[] args) {
        System.out.println("play:");
        System.out.println(PlayerUp.getImageLoadStatus());//若返回8表示读取正确，否则读取失败
        System.out.println(PlayerDown.getImageLoadStatus());
        System.out.println(PlayerLeft.getImageLoadStatus());
        System.out.println(PlayerRight.getImageLoadStatus());

        System.out.println("bullet");
        System.out.println(BulletUp.getImageLoadStatus());
        System.out.println(BulletDown.getImageLoadStatus());
        System.out.println(BulletLeft.getImageLoadStatus());
        System.out.println(BulletRight.getImageLoadStatus());

        System.out.println("enemy green:");
        System.out.println(EnemyGreenUp.getImageLoadStatus());
        System.out.println(EnemyGreenDown.getImageLoadStatus());
        System.out.println(EnemyGreenLeft.getImageLoadStatus());
        System.out.println(EnemyGreenRight.getImageLoadStatus());

        System.out.println("enemy white:");
        System.out.println(EnemyWhiteUp.getImageLoadStatus());
        System.out.println(EnemyWhiteDown.getImageLoadStatus());
        System.out.println(EnemyGreenLeft.getImageLoadStatus());
        System.out.println(EnemyWhiteRight.getImageLoadStatus());

        System.out.println("enemy red:");
        System.out.println(EnemyRedUp.getImageLoadStatus());
        System.out.println(EnemyRedDown.getImageLoadStatus());
        System.out.println(EnemyRedLeft.getImageLoadStatus());
        System.out.println(EnemyRedRight.getImageLoadStatus());

        System.out.println("enemy yellow:");
        System.out.println(EnemyYellowUp.getImageLoadStatus());
        System.out.println(EnemyYellowDown.getImageLoadStatus());
        System.out.println(EnemyYellowLeft.getImageLoadStatus());
        System.out.println(EnemyYellowRight.getImageLoadStatus());

        System.out.println("born:");
        System.out.println(Born1.getImageLoadStatus());
        System.out.println(Born2.getImageLoadStatus());
        System.out.println(Born3.getImageLoadStatus());
        System.out.println(Born4.getImageLoadStatus());

        System.out.println("boom:");
        System.out.println(Boom.getImageLoadStatus());

        System.out.println("background:");
        System.out.println(BackGround.getImageLoadStatus());
    }

}
