package animation;
import biuoop.DrawSurface;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 15.6.2021
 */
public interface Animation {
    /**
     * does one frame on given draw surface.
     * @param d draw surface given
     */
    void doOneFrame(DrawSurface d);

    /**
     * checks if animation should stop.
     * @return boolean true or false
     */
    boolean shouldStop();
}
