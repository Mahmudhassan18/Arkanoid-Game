package geometry;

import collisiondetection.Collidable;

import java.util.List;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.4.2021
 */
public class Point {
    public static final int TWO = 2;
    private double x;
    private double y;
    /**
     * point constructor.
     * @param x point x
     * @param y point y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculates the distance of two points.
     * @param other other line given
     * @return returns the distance.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.getX() - other.getX(), TWO) + Math.pow(this.getY() - other.getY(), TWO));
    }

    /**
     * return true if the points are equal, false otherwise.
     * @param other other line given
     * @return true or false
     */
    public boolean equals(Point other) {
        return (this.getX() == other.getX() && this.getY() == other.getY());
    }

    /**
     * accessor of point x.
     * @return x of given point
     */
    public double getX() {
        return this.x;
    }
    /**
     * accessor of point y.
     * @return y of given point
     */
    public double getY() {
        return this.y;
    }

    /**
     * returns the object the ball collided with.
     * @param collidables list of collidables
     * @return the object the ball collided with
     */
    public Collidable getCollisionObject(List<Collidable> collidables) {
        //goes over every collidable
        for (Collidable c : collidables) {
            //gets 4 lines of rectangle
            Line l1 = c.getCollisionRectangle().getUpperLine();
            Line l2 = c.getCollisionRectangle().getBottomLine();
            Line l3 = c.getCollisionRectangle().getRightLine();
            Line l4 = c.getCollisionRectangle().getLeftLine();
            // check if point's x & y in on any line of that rectangle
            if (l1.isXInRange(this.getX(), l2) || l3.isXInRange(this.getX(), l4)) {
                if (l1.isYInRange(this.getY(), l2) || l3.isYInRange(this.getY(), l4)) {
                    return c;

                }
            }
        }
        return null;
    }
}