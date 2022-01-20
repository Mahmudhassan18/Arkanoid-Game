package geometry;
import ballsettings.Ball;
import sprites.Sprite;
import collisiondetection.Collidable;
import gamesettings.GameLevel;
import ballsettings.Velocity;
import biuoop.DrawSurface;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.4.2021
 */
public class Paddle implements Sprite, Collidable {
    private static final double ANGLE_300 = 300;
    private static final double ANGLE_30 = 30;
    private static final double FIVE = 5;
    private static final double MAX_X = 780;
    private static final double MIN_X = 20;
    private Rectangle rectangle;
    private final java.awt.Color color;
    private final biuoop.KeyboardSensor keyboard;
    private final int paddleSpeed;

    /**
     * paddle constructor.
     * @param rect rectangle that's paddle created from
     * @param color paddle's color
     */
    public Paddle(Rectangle rect, java.awt.Color color, int paddleSpeed, biuoop.KeyboardSensor ks) {
        this.color = color;
        this.rectangle = rect;
        this.paddleSpeed = paddleSpeed;
        this.keyboard = ks;
    }

    /**
     * moves paddle left by changes it's upper left x.
     */
    public void moveLeft() {
        Point point = this.rectangle.getUpperLeft();
        //checks if paddle reached min x
        if (point.getX() - this.paddleSpeed > MIN_X) {
            //subtracts upper left position by 5
            this.rectangle = new Rectangle(new Point(point.getX() - paddleSpeed, point.getY()), this.rectangle.getWidth(), this.rectangle.getHeight());
        }
        else {
            this.rectangle = new Rectangle(new Point(MIN_X, point.getY()), this.rectangle.getWidth(), this.rectangle.getHeight());
        }

    }
    /**
     * moves paddle right by changes it's upper left x.
     */
    public void moveRight() {
        Point point = this.rectangle.getUpperLeft();
        //checks if upper point + width reached the end
        if (point.getX() + this.rectangle.getWidth() < MAX_X) {
            Rectangle r = this.rectangle;
            //adds five to upper left
            this.rectangle = new Rectangle(new Point(point.getX() + paddleSpeed, point.getY()), r.getWidth(), r.getHeight());
        }
        else {
            this.rectangle = new Rectangle(new Point(MAX_X - this.rectangle.getWidth(), point.getY()), this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * Checks if left or right arrow is pressed.
     * and calls the functions accordingly
     * this func implements sprite
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * draws paddle on drawing surface.
     * this func implements sprite
     * @param d drawing surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        Rectangle r = this.rectangle;
        Point upperLeft = r.getUpperLeft();
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) r.getWidth(), (int) r.getHeight());
    }

    /**
     * rectangle accessor.
     * @return paddle's rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * checks where the ball collided on the paddle.
     * if it's in upper side it changes it velocity by the area it got collided
     * else it changes accordingly
     * @param collisionPoint Collision point
     * @param currentVelocity Ball velocity before hit.
     * @param hitter The ball that hits the blocks.
     * @return new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //collision x and y values
        double cX = collisionPoint.getX();
        double cY = collisionPoint.getY();
        //4 lines of the paddles
        Line l1 = this.rectangle.getUpperLine();
        Line l2 = this.rectangle.getBottomLine();
        Line l3 = this.rectangle.getRightLine();
        Line l4 = this.rectangle.getLeftLine();
        //if collision on upper line
        if (l1.isXInRange(cX, l1)) {
            //upper line length by 5
            double distance = l1.length() / FIVE;
            double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
            //checks on which part the ball collided and changes velocity accordingly
            for (int i = 0; i < FIVE; i++) {
                double startX = l1.start().getX();
                if (isInRange(cX, startX + (i * distance), startX + ((i + 1) * distance))) {
                    if (i < 2) {
                        return Velocity.fromAngleAndSpeed(ANGLE_300 + (i * ANGLE_30), speed);
                    }
                    if (i == 2) {
                        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                    } else {
                        return Velocity.fromAngleAndSpeed(ANGLE_30 + ((i % 3) * ANGLE_30), speed);
                    }
                }
            }
        }
        //checks if it's on bottom line
        if (l2.isXInRange(cX, l2) && l2.isYInRange(cY, l2)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //checks if it's on left or right line
        if (l3.isXInRange(cX, l3) && l3.isYInRange(cY, l3) || l4.isXInRange(cX, l4) && l4.isYInRange(cY, l4)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return currentVelocity;
        }
    }

    /**
     * Cheks if x in in range of min and max.
     * @param collisionX collision x
     * @param min min x
     * @param max max x
     * @return boolean true or false
     */
    private boolean isInRange(double collisionX, double min, double max) {
        return (collisionX >= min && collisionX <= max);
    }

    /**
     * Add this paddle to the game.
     * @param g game given
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}