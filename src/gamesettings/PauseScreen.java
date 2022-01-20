package gamesettings;
import biuoop.DrawSurface;
import animation.Animation;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 15.6.2021
 */
public class PauseScreen implements Animation {

    /**
     * pause screen constructor.
     */
    public PauseScreen() {

    }

    /**
     * draws the pause screen text.
     * @param d draw surface given
     */
    public void doOneFrame(DrawSurface d) {
        //draws paused on draw surface
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * returns if animation should stop.
     * @return boolean false or true
     */
    public boolean shouldStop() {
        return false;
    }
}