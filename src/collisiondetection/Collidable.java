package collisiondetection;

import geometry.Rectangle;
import geometry.Point;
import ballsettings.Ball;
import ballsettings.Velocity;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.5.2021
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return Collision rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint Collision point
     * @param currentVelocity Ball velocity before hit.
     * @param hitter The ball that hits the block
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}