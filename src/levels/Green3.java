package levels;

import ballsettings.Velocity;
import geometry.Block;
import geometry.Rectangle;
import geometry.Point;
import sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Green3 implements LevelInformation{
    private static final int FIVE_LINES = 5;
    private static final int MAX_REC_IN_LINE = 12;
    private static final int FIRST_X = 180;
    private static final int FIRST_Y = 150;
    private static final double WIDTH = 50;
    private static final double HEIGHT = 25;
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        double speed = 10;
        Velocity v1 = Velocity.fromAngleAndSpeed(45, speed);
        Velocity v2 = Velocity.fromAngleAndSpeed(-45, speed);
        ballsVelocity.add(v1);
        ballsVelocity.add(v2);
        return ballsVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 85;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int y = 150;
        for (int i = 0; i < FIVE_LINES; i++) {
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color color = new Color(r, g, b);
            for (int j = i + 5; j < 15; j++) {
                Point l1p1 = new Point(j * 50 + 30, y);
                Rectangle rec = new Rectangle(l1p1, WIDTH, HEIGHT);
                Block block = new Block(rec, color);
                blocks.add(block);
            }
            y += HEIGHT;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
