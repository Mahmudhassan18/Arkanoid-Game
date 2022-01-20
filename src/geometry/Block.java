package geometry;

import ballsettings.Ball;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Sprite;
import collisiondetection.Collidable;
import gamesettings.GameLevel;
import ballsettings.Velocity;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.5.2021
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private final java.awt.Color color;
    private final List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Block constructor creates new blocks.
     * @param rect rectangle to create block from.
     * @param color color of block.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.color = color;
        this.rectangle = rect;
    }

    public Block(Point point, double width, double height, java.awt.Color color) {
        this.rectangle = new Rectangle(point, width, height);
        this.color = color;
    }

    /**
     * Block color getter (accessor).
     * @return blocks color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws block on drawing surface.
     * @param surface drawing surface.
     */
    public void drawOn(DrawSurface surface) {
        //rectangle block created from.
        Rectangle rec = this.rectangle;
        //blocks upper left corner.
        Point upper = rec.getUpperLeft();
        surface.setColor(this.getColor());
        //fills a rectangle.
        surface.fillRectangle((int) upper.getX(), (int) upper.getY(), (int) rec.getWidth(), (int) rec.getHeight());
        surface.setColor(java.awt.Color.black);
        //draws rectangle lines.
        surface.drawRectangle((int) upper.getX(), (int) upper.getY(), (int) rec.getWidth(), (int) rec.getHeight());
    }

    @Override
    /**
     * times passes does nothing to block.
     */
    public void timePassed() {

    }

    /**
     * adds block to a game with adding it to sprite and colliadble.
     * @param g Given game.
     */
    public void addToGame(GameLevel g) {
        //adds block to sprite and collidables
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * blocks rectangle getter (accessor) interface method.
     * @return rectangle that block created from
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Checks if collision point is in top lines or side lines and changes speed according.
     * @param collisionPoint Collision point with rectangle.
     * @param currentVelocity Ball's velocity.
     * @param hitter ball that hits the blocks.
     * @return New velocity according to the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //Collision X & Y
        double cX = collisionPoint.getX();
        double cY = collisionPoint.getY();
        // 4 Lines of the rectangle that is hit
        Line l1 = this.rectangle.getUpperLine();
        Line l2 = this.rectangle.getBottomLine();
        Line l3 = this.rectangle.getRightLine();
        Line l4 = this.rectangle.getLeftLine();
        //Checks if collision point in upper or bottom lines
        if (l1.isXInRange(cX, l1) && l1.isYInRange(cY, l1) || l2.isXInRange(cX, l2) && l2.isYInRange(cY, l2)) {
            //Changes DY to -DY
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //Checks if collision point in upper or bottom lines
        if (l3.isXInRange(cX, l3) && l3.isYInRange(cY, l3) || l4.isXInRange(cX, l4) && l4.isYInRange(cY, l4)) {
            //Changes DX to -DX
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            //If no hit returns velocity with no change
            return currentVelocity;
        }
    }

    /**
     * removes block from sprite and collidables.
     * @param gameLevel game to remove block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * notifies all listeners that hit occurred.
     * @param hitter the ball that makes the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
