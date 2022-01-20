package levels;

import ballsettings.Velocity;
import geometry.Block;
import geometry.Point;
import sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FinalFour implements LevelInformation {
    private static final double WIDTH =(760.00 / 15.00);
    private static final double HEIGHT = 25;
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocity = new ArrayList<>();
        double speed = 8;
        for (int i = 0; i < 3; i++){
            velocity.add(Velocity.fromAngleAndSpeed(-45 + (i * 45), speed));
        }
        return velocity;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color color = new Color(r, g, b);
            for (int j = 0; j < 15; j++){
                Point point = new Point(20 + (WIDTH * j), 100 + (HEIGHT * i));
                blocks.add(new Block(point, WIDTH, HEIGHT, color));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
