package listeners;

import ballsettings.Ball;
import counter.Counter;
import geometry.Block;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.5.2021
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * constructor that keeps track of current score.
     * @param scoreCounter current score count
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }


    /**
     * if triggered adds 5 to score.
     * @param beingHit Block that is being hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }

}
