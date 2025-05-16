
import java.awt.Point;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private static final int TILE_SIZE = 20;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private final ArrayList<Point> snake = new ArrayList<>();
    private Point food;
    private char direction = 'R';
    private boolean running = false;
    private final Random random = new Random();

    public GameLogic() {
        resetGame();
    }

    public void resetGame() {
        snake.clear();
        snake.add(new Point(5, 5));
        spawnFood();
        direction = 'R';
        running = true;
    }

    public void spawnFood() {
        food = new Point(random.nextInt(WIDTH / TILE_SIZE), random.nextInt(HEIGHT / TILE_SIZE));
    }

    public void move() {
        if (!running) return;

        Point head = new Point(snake.get(0));
        switch (direction) {
            case 'U' -> head.y--;
            case 'D' -> head.y++;
            case 'L' -> head.x--;
            case 'R' -> head.x++;
        }

        // Wrap around screen and appears from another side
        if (head.x < 0) head.x = WIDTH / TILE_SIZE - 1;
        if (head.x >= WIDTH / TILE_SIZE) head.x = 0;
        if (head.y < 0) head.y = HEIGHT / TILE_SIZE - 1;
        if (head.y >= HEIGHT / TILE_SIZE) head.y = 0;

        // Collied with self
        if (snake.contains(head)) {
            running = false;
            return;
        }

        snake.add(0, head);

        if (head.equals(food)) {
            spawnFood();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    public void changeDirection(char newDirection) {
        if ((newDirection == 'L' && direction != 'R') ||
            (newDirection == 'R' && direction != 'L') ||
            (newDirection == 'U' && direction != 'D') ||
            (newDirection == 'D' && direction != 'U')) {
            direction = newDirection;
        }
    }

    public boolean isRunning() {
        return running;
    }

    public ArrayList<Point> getSnake() {
        return snake;
    }
    public Point getFood() {
        return food;
    }
}
