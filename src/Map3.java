import java.awt.*;
import java.util.ArrayList;

public class Map3 extends Map {
    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    int[][] obstacleArr3 = {{},
            {},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 4, 4, 0, 0, 4, 4, 1, 0},
            {1, 0, 4, 4, 0, 0, 4, 4, 0, 1},
            {0, 1, 4, 4, 4, 4, 4, 4, 1, 0},
            {0, 0, 1, 2, 2, 2, 2, 1, 0, 0},
            {0, 0, 0, 1, 2, 3, 1, 0, 0, 0},
    };

    public Map3() {
        super();
        for (int i = 0; i < obstacleArr3.length; i++) {
            for (int j = 0; j < obstacleArr3[i].length; j++) {
                if (obstacleArr3[i][j] == 1) {
                    obstacles.add(new Obstacle(j * 70, i * 70, Obstacle.obstacle.wall));
                } else if (obstacleArr3[i][j] == 2) {
                    obstacles.add(new Obstacle(j * 70, i * 70, Obstacle.obstacle.grass));
                }
                if (obstacleArr3[i][j] == 3) {
                    obstacles.add(new Obstacle(j * 70, i * 70, Obstacle.obstacle.home));
                } else if (obstacleArr3[i][j] == 4) {
                    obstacles.add(new Obstacle(j * 70, i * 70, Obstacle.obstacle.sea));
                }
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < obstacles.size(); i++) {
            if (!obstacles.get(i).isLive()) {
                obstacles.get(i).draw();
                g.drawImage(obstacles.get(i).getObstacleImage(), obstacles.get(i).x, obstacles.get(i).y, 70, 70, null);
            } else {
                obstacles.remove(i);
            }
        }
    }
}
