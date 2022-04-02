import java.awt.*;
import java.util.ArrayList;

public class Map5 extends Map {
    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    int[][] obstacleArr1 = {{},
            {},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 3, 1, 1, 1, 0},
            {0, 2, 0, 1, 1, 1, 1, 1, 0, 2},
            {0, 2, 2, 0, 1, 1, 1, 0, 2, 2},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
    };

    public Map5() {
        super();
        for (int i = 0; i < obstacleArr1.length; i++) {
            for (int j = 0; j < obstacleArr1[i].length; j++) {
                if (obstacleArr1[i][j] == 1) {
                    obstacles.add(new Obstacle(j * 70, i * 70, Obstacle.obstacle.wall));
                } else if (obstacleArr1[i][j] == 2) {
                    obstacles.add(new Obstacle(j * 70, i * 70, Obstacle.obstacle.grass));
                }
                if (obstacleArr1[i][j] == 3) {
                    obstacles.add(new Obstacle(j * 70, i * 70, Obstacle.obstacle.home));
                } else if (obstacleArr1[i][j] == 4) {
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

    public void drawGrass(Graphics g) {
        for (int i = 0; i < obstacles.size(); i++) {
            if (obstacles.get(i).getkind() == Obstacle.obstacle.grass) {
                obstacles.get(i).draw();
                g.drawImage(obstacles.get(i).getObstacleImage(), obstacles.get(i).x, obstacles.get(i).y, 70, 70, null);
            }
        }
    }

}
