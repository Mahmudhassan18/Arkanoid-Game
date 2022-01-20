package sprites;
import biuoop.DrawSurface;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.5.2021
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d drawing surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}