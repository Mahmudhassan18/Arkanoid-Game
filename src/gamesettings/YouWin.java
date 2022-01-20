package gamesettings;

import animation.Animation;
import biuoop.DrawSurface;
import counter.Counter;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 15.6.2021
 */
public class YouWin implements Animation {

    private final Counter score;

    /**
     * you win constructor with score.
     * @param score score of the game
     */
    public YouWin(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //draws texts on draw surface
        d.drawText(150, 200, "YOU WIN!", 50);
        d.drawText(150, 500, "PRESS SPACE TO EXIT", 40);
        d.drawText(150, 300, "Score: " + score.getValue(), 50);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
