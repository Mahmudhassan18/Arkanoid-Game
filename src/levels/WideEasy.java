package levels;

import ballsettings.Velocity;
import geometry.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WideEasy implements LevelInformation {
    private static final double WIDTH =(760.00 / 15.00);
    private static final double HEIGHT = 25;
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocity = new ArrayList<>();
        double speed = 10;
        for (int i = 0; i < 10; i++){
           ballVelocity.add(Velocity.fromAngleAndSpeed(50 - (i * 10), speed));
        }
        return ballVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 650;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        for(int i = 0 ; i < 15; i++) {
            Point point = new Point(20 + (50 * i), 150);
            points.add(point);
        }
        blocks.add(new Block(points.get(0), WIDTH, HEIGHT,Color.RED));
        blocks.add(new Block(points.get(1), WIDTH, HEIGHT,Color.RED));
        blocks.add(new Block(points.get(2), WIDTH, HEIGHT,Color.orange));
        blocks.add(new Block(points.get(3), WIDTH, HEIGHT,Color.orange));
        blocks.add(new Block(points.get(4), WIDTH, HEIGHT,Color.yellow));
        blocks.add(new Block(points.get(5), WIDTH, HEIGHT,Color.yellow));
        blocks.add(new Block(points.get(6), WIDTH, HEIGHT,Color.green));
        blocks.add(new Block(points.get(7), WIDTH, HEIGHT,Color.green));
        blocks.add(new Block(points.get(8), WIDTH, HEIGHT,Color.green));
        blocks.add(new Block(points.get(9), WIDTH, HEIGHT,Color.blue));
        blocks.add(new Block(points.get(10), WIDTH, HEIGHT,Color.blue));
        blocks.add(new Block(points.get(11), WIDTH, HEIGHT,Color.pink));
        blocks.add(new Block(points.get(12), WIDTH, HEIGHT,Color.pink));
        blocks.add(new Block(points.get(13), WIDTH, HEIGHT,Color.cyan));
        blocks.add(new Block(points.get(14), WIDTH, HEIGHT,Color.cyan));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
