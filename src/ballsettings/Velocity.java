package ballsettings;

import geometry.Point;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 1.4.2021
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * Velocity constructor.
     * @param dx velocity in DX
     * @param dy velocity in DY
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates new velocity by angle and speed.
     * gets DX by multiplying sin(angle)*speed
     * gets DY by multiplying cos(angle)*speed
     * @param angle angle given
     * @param speed speed in the angle given
     * @return returns velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * Accessor for velocity construct.
     * @return returns velocity DX
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Accessor for velocity construct.
     * @return returns velocity DY
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * Takes a point with position (x,y) and return a new point.
     * @param p point given
     * @return (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(this.getDx() + p.getX(), this.getDy() + p.getY());

    }
}
