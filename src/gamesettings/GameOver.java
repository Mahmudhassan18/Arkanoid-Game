package gamesettings;

import animation.Animation;
import biuoop.DrawSurface;
import counter.Counter;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 15.6.2021
 */
public class GameOver implements Animation {
    private final Counter score;

    /**
     * game over constructor.
     * @param score game score
     */
    public GameOver(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, 200, "YOU LOST!", 50);
        d.drawText(150, 500, "PRESS SPACE TO EXIT", 40);
        d.drawText(150, 300, "Score: " + score.getValue(), 50);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
