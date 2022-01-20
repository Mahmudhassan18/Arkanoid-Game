package collisiondetection;
import geometry.Point;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.5.2021
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Collision info constructor.
     * @param collisionPoint Collision point
     * @param collisionObject Object the the ball collided with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Collision point getter (accessor).
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    // the collidable object involved in the collision.
    /**
     * Collidable object getter (accessor).
     * @return Collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}