package listeners;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.5.2021
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl hit listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl hit listener to remove
     */
    void removeHitListener(HitListener hl);
}