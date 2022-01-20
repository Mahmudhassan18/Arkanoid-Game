package levels;

import ballsettings.Velocity;
import biuoop.DrawSurface;
import geometry.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class DirectHit implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocity = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(0, 8);
        ballVelocity.add(v1);
        return ballVelocity;
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Point p1 = new Point(385, 150);
        Rectangle rec = new Rectangle(p1, 30, 30);
        Block b1 = new Block(rec, Color.red);
        blocks.add(b1);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
