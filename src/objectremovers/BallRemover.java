package objectremovers;

import ballsettings.Ball;
import geometry.Block;
import listeners.HitListener;
import counter.Counter;
import gamesettings.GameLevel;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.5.2021
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Ball remover constructor stores game and num of remaining balls.
     * @param gameLevel game that the balls and block in
     * @param removedBalls Num of removed balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }


    /**
     * Method triggered when a hit occurs and removes ball from game.
     * @param beingHit block that is being hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}
