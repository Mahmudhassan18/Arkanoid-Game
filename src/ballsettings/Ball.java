package ballsettings;
import sprites.Sprite;
import geometry.Line;
import geometry.Point;
import collisiondetection.Collidable;
import collisiondetection.CollisionInfo;
import gamesettings.GameEnvironment;
import gamesettings.GameLevel;
import java.awt.Color;
import biuoop.DrawSurface;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.4.2021
 */
public class Ball implements Sprite {
    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;
    private final GameEnvironment gameEnvironment;


    /**
     * Ball constructor that saves center radius and ball color.
     *
     * @param x     center x position
     * @param y     center y position
     * @param r     ball's radius
     * @param color ball's color
     * @param environment environment were working with
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.gameEnvironment = environment;
        new Ball(center, r, color, environment);
    }

    /**
     * Ball constructor but receives center point.
     * @param center center point
     * @param r      ball's radius
     * @param color  ball's color
     * @param environment environment were working with.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = environment;
    }

    /**
     * Center point getter (accessor).
     * @return Ball's center point.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Center x position accessor.
     *
     * @return the x center position.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Center y position accessor.
     *
     * @return the y center position.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Ball's radius accessor.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Ball's color accessor.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface The drawing surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());

    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }


    /**
     * Sets the velocity of the balls.
     *
     * @param v Velocity given to get set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * Sets the velocity of the balls.
     *
     * @param dx velocity in the x axis
     * @param dy velocity in y axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Ball's velocity accessor.
     *
     * @return the ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Adds ball to game which is a sprite.
     * @param g Game were working with.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Changes the ball center according to the collisions.
     */
    public void moveOneStep() {
        //ball's center X & Y
        double centerX = this.getCenter().getX();
        double centerY = this.getCenter().getY();
        Point collisionP = null;
        Collidable collisionRec = null;
        Point collisionPX = null;
        Point collisionPY = null;
        //Ball's velocity dx & dy
        double dxVelocity = this.getVelocity().getDx();
        double dyVelocity = this.getVelocity().getDy();
        //game environment were working with
        GameEnvironment enviro = this.gameEnvironment;
        //Balls trajectory in middle
        Line trajectory = new Line(centerX, centerY, centerX + (dxVelocity * 2), centerY + (dyVelocity * 2));
        //Balls trajectory in the x axis
        Line trajectoryX = new Line(centerX, centerY, centerX + dxVelocity, centerY);
        //Balls trajectory in the y axis
        Line trajectoryY = new Line(centerX, centerY, centerX, centerY + dyVelocity);
        //checks if there's a collision and get's it's info
        if (this.gameEnvironment.getClosestCollision(trajectory) != null) {
            CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
            collisionRec = collisionInfo.collisionObject();
            collisionP = collisionInfo.collisionPoint();
        }
        //checks if there's a collision in the x and y axis
        if (enviro.getClosestCollision(trajectoryX) != null && enviro.getClosestCollision(trajectoryY) != null) {
            //gets collision info in the x and y axis
            CollisionInfo collisionInfoX = this.gameEnvironment.getClosestCollision(trajectoryX);
            CollisionInfo collisionInfoY = this.gameEnvironment.getClosestCollision(trajectoryY);
            collisionPX = collisionInfoX.collisionPoint();
            collisionPY = collisionInfoY.collisionPoint();
            //checks if a collision in y and x is on the radius of the ball
            if (Math.pow(centerX - collisionPX.getX(), 2) == Math.pow(centerY - collisionPY.getY(), 2)) {
                //if so it inverts the dy velocity
                this.setVelocity(new Velocity(this.getVelocity().getDx(), -this.getVelocity().getDy()));
                this.center = this.getVelocity().applyToPoint(this.getCenter());
            }
        }
        //checks if there's a collision happend
        if (collisionP != null) {
            //goes over all the collision options and changes their velocity accordingly.
            if (centerX > collisionP.getX()) {
                if (this.center.getX() + this.radius >= collisionP.getX()) {
                    this.setVelocity(collisionRec.hit(this, collisionP, this.getVelocity()));
                    this.center = this.getVelocity().applyToPoint(this.getCenter());
                }
            }
            if (centerX < collisionP.getX()) {
                if (this.center.getX() - this.radius <= collisionP.getX()) {
                    this.setVelocity(collisionRec.hit(this, collisionP, this.getVelocity()));
                    this.center = this.getVelocity().applyToPoint(this.getCenter());
                }
            }
            if (centerX == collisionP.getX()) {
                if (this.center.getY() - this.radius <= collisionP.getY()) {
                    this.setVelocity(collisionRec.hit(this, collisionP, this.getVelocity()));
                    this.center = this.getVelocity().applyToPoint(this.getCenter());
                }
                if (this.center.getY() + this.radius >= collisionP.getY()) {
                    this.setVelocity(collisionRec.hit(this, collisionP, this.getVelocity()));
                    this.center = this.getVelocity().applyToPoint(this.getCenter());
                }
            }
            //if no collision happened keeps same velocity and changes the center
        } else {
            this.center = this.getVelocity().applyToPoint(this.getCenter());
        }
    }

    /**
     * Removes ball from game by removing it from sprite.
     * @param gameLevel Game given to remove the ball from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}

