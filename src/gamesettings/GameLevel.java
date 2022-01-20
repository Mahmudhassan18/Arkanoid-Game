package gamesettings;
import animation.Animation;
import animation.AnimationRunner;
import ballsettings.Velocity;
import levels.LevelInformation;
import listeners.ScoreTrackingListener;
import sprites.Sprite;
import sprites.SpriteCollection;
import geometry.Point;
import geometry.Rectangle;
import geometry.Block;
import geometry.Paddle;
import collisiondetection.Collidable;
import ballsettings.Ball;
import biuoop.DrawSurface;
import indicators.ScoreIndicator;
import counter.Counter;
import java.awt.Color;

import objectremovers.BallRemover;
import objectremovers.BlockRemover;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.4.2021
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final Counter blocksCounter = new Counter();
    private final Counter ballsCounter = new Counter();
    private final Counter score;
    private boolean running;
    private final AnimationRunner runner;
    private final biuoop.KeyboardSensor keyboard;
    private final LevelInformation level;

    /**
     * Game level constructor.
     * @param level Level to use.
     * @param runner animation runner
     * @param keyboard keyboard sensor
     * @param score game score
     */
    public GameLevel(LevelInformation level, AnimationRunner runner, biuoop.KeyboardSensor keyboard, Counter score) {
        this.level = level;
        this.runner = runner;
        this.keyboard = keyboard;
        this.score = score;
    }
    /**
     * Adds collidable.
     * @param c Collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds sprite to sprites.
     * @param s sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Creates borders and adds them to game.
     */
    public void bordersCreator() {
        Rectangle r1 = new Rectangle(new Point(0, 40), 20, 600);
        Block b1 = new Block(r1, Color.gray);
        Rectangle r2 = new Rectangle(new Point(780, 40), 20, 600);
        Block b2 = new Block(r2, Color.gray);
        Rectangle r3 = new Rectangle(new Point(0, 20), 800, 20);
        Block b3 = new Block(r3, Color.gray);
        Rectangle r4 = new Rectangle(new Point(0, 600), 800, 20);
        Block b4 = new Block(r4, Color.gray);
        BallRemover ballRemoveLis = new BallRemover(this, ballsCounter);
        b4.addHitListener(ballRemoveLis);
        b1.addToGame(this);
        b2.addToGame(this);
        b3.addToGame(this);
        b4.addToGame(this);
    }

    /**
     * initializes blocks and adds block to game and one to counter.
     */
    public void initializeBlocks() {
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(score);
        for (Block block: level.blocks()) {
            BlockRemover blockRemoverLis = new BlockRemover(this, blocksCounter);
            block.addToGame(this);
            block.addHitListener(scoreTracking);
            block.addHitListener(blockRemoverLis);
            blocksCounter.increase(1);
        }
    }

    /**
     * initializes balls and adds balls to game and one to counter.
     */
    public void initializeBalls() {
        for (Velocity velocity: level.initialBallVelocities()) {
            Ball ball = new Ball(400, 500, 8, Color.black, environment);
            ball.setVelocity(velocity);
            ball.addToGame(this);
            ballsCounter.increase(1);
        }
    }

    /**
     * initializes game paddle and adds it to game.
     */
    public void initializePaddle() {
        Point point = new Point(400 - (double) (level.paddleWidth() / 2), 560);
        Rectangle rectangle = new Rectangle(point, level.paddleWidth(), 20);
        Paddle paddle = new Paddle(rectangle, Color.black, level.paddleSpeed(), keyboard);
        paddle.addToGame(this);
    }

    /**
     * initializes game with all of it's components.
     */
    public void initialize() {
        //creates borders, blocks, balls, paddle and scored indicators.
        bordersCreator();
        initializeBlocks();
        initializeBalls();
        initializePaddle();
        ScoreIndicator scoreInd = new ScoreIndicator(score);
        scoreInd.addToGame(this);
    }

    /**
     *Run the game start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites, level));
        this.running = true;
        this.runner.run(this);

    }

    /**
     * removes collidable from environment.
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     *removes sprite from environment.
     * @param s sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        d.drawText(600, 15, "Level Name: " + level.levelName(), 15);
        if (keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", new PauseScreen()));
        }
        if (blocksCounter.getValue() == 0) {
            score.increase(100);
            this.running = false;
        }
        if (ballsCounter.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * gets number of remaining blocks.
     * @return number of blocks remaining
     */
    public int getRemainingBlocks() {
        return this.blocksCounter.getValue();
    }

    /**
     * get number of remaining balls.
     * @return number of remaining balls
     */
    public int getRemainingBalls() {
        return this.ballsCounter.getValue();
    }

}