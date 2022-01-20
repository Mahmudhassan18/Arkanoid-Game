package indicators;

import biuoop.DrawSurface;
import counter.Counter;
import sprites.Sprite;
import gamesettings.GameLevel;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.5.2021
 */
public class ScoreIndicator implements Sprite {
    private final Counter currentScore;

    /**
     * ScoreIndicator constructor that stores current score.
     * @param score current score
     */
    public ScoreIndicator(Counter score) {
        this.currentScore = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(380, 15, "Score: " + this.currentScore.getValue(), 15);
    }

    @Override
    public void timePassed() {

    }

    /**
     * adds score to sprites.
     * @param g game given
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

