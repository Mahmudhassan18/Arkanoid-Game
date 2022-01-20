package sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.4.2021
 */
public class SpriteCollection {
    private final List<Sprite> sprites = new ArrayList<>();

    /**
     * adds sprite to sprites array list.
     * @param s sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removes given sprite from sprites collection.
     * @param s sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesC = new ArrayList<>(this.sprites);
        for (Sprite s : spritesC) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d drawing surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}