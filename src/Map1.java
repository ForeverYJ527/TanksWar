
import java.awt.*;
import java.util.ArrayList;
public class Map1 extends Map{
    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }
    int[][] obstacleArr1 = {{}, // 0为空 1为墙 2为草 3目标4为海洋
            {}, // 墙体位置
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 3, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 4, 4, 4, 4, 4, 4, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},



    };
    public Map1() {//根据数组情况构造墙
        super();
        for (int i = 0; i < obstacleArr1.length; i++) {
            for (int j = 0; j < obstacleArr1[i].length; j++) {
                if (obstacleArr1[i][j] == 1) {//土墙数组
                    obstacles.add(new Obstacle(j * 70, i *70, Obstacle.obstacle.wall));
                }else if(obstacleArr1[i][j]==2){//草的数组
                    obstacles.add(new Obstacle(j*70, i*70, Obstacle.obstacle.grass));
                }
                if (obstacleArr1[i][j] == 3) {//目标数组
                    obstacles.add(new Obstacle(j * 70, i * 70, Obstacle.obstacle.home));
                }else if (obstacleArr1[i][j]==4) {//海洋数组
                    obstacles.add(new Obstacle(j*70,i*70, Obstacle.obstacle.sea));
                }
            }
        }
    }
    public void draw(Graphics g) {//绘制墙7
        for(int i= 0;i<obstacles.size();i++){
            if(!obstacles.get(i).isLive()){
                obstacles.get(i).draw();
                g.drawImage(obstacles.get(i).getObstacleImage(), obstacles.get(i).x, obstacles.get(i).y, 70,70, null);
            }else{
                obstacles.remove(i);
            }
        }
    }

}
