package gamesettings;

import geometry.Point;
import geometry.Line;
import collisiondetection.Collidable;
import collisiondetection.CollisionInfo;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.4.2021
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();


    /**
     * add the given collidable to the environment.
     * @param c Collidable given.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection.
     * @param trajectory Line that the ball is moving on
     * @return return null. Else, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> interPoints = new ArrayList<>();
        List<Collidable> collidablesL = new ArrayList<>(this.collidables);
        //goes over every collidable
        for (Collidable c : collidablesL) {
            //checks if there's a collision if so it adds it to interpoints
            Point intersection = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (intersection != null) {
                interPoints.add(intersection);
            }
        }
        //if no collision happened it returns null
        if (interPoints.size() == 0) {
            return null;
        }
        //compares every collision point and returns the closest .
        Point closest = trajectory.closestPointToStart(interPoints);
        Collidable collisionObject = closest.getCollisionObject(collidablesL);
        return new CollisionInfo(closest, collisionObject);
    }

    /**
     * remover given collidable from collidables.
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}