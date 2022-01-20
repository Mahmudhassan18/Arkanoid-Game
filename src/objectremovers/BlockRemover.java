package objectremovers;

import ballsettings.Ball;
import geometry.Block;
import listeners.HitListener;
import counter.Counter;
import gamesettings.GameLevel;

// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.5.2021
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Block remover responsible for removing block from game.
     * and keeping track of the number of the blocks remaining
     * @param gameLevel game that the block and balls in
     * @param removedBlocks num of removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }


    /**
     * Method triggered when a hit occurs and removes block from game.
     * @param beingHit block that is being hit
     * @param hitter the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
    }
}
