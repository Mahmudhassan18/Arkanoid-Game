package listeners;

import geometry.Block;
import ballsettings.Ball;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.5.2021
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit Block that is being hit
     * @param hitter the ball that hits the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
