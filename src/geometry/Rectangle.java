package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.4.2021
 */
public class Rectangle {
    private final Point upperLeft;
    private final double height;
    private final double width;

    /**
     * create a new rectangle with location and width/height.
     * @param upperLeft rectangle upperleft point
     * @param width rectangle width
     * @param height rectangle height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line line to check intersections with.
     * @return list of intersections
     */
    public java.util.List<Point> intersectionPoints(Line line) {
      List interList = new ArrayList();
      //gets intersection points
      interList = line.getIntersectionPoints(this);
      return interList;
    }

    /**
     * Rectangle width getter (accessor).
     * @return rectangle's width
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Rectangle height getter (accessor).
     * @return rectangle's height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Rectangle width getter (accessor).
     * @return rectangle's width
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * creates new line that represents the upper rectangle's line.
     * @return upper line of the rectangle
     */
    public Line getUpperLine() {
        double w = this.getWidth();
        return new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + w, upperLeft.getY());
    }
    /**
     * creates new line that represents the bottom rectangle's line.
     * @return bottom line of the rectangle
     */
    public Line getBottomLine() {
        double h = this.getHeight();
        double w = this.getWidth();
         return new Line(upperLeft.getX(), upperLeft.getY() + h, upperLeft.getX() + w, upperLeft.getY() + h);
    }
    /**
     * creates new line that represents the right rectangle's line.
     * @return right line of the rectangle
     */
    public Line getRightLine() {
        double h = this.getHeight();
        double w = this.getWidth();
        return new Line(upperLeft.getX() + w, upperLeft.getY(), upperLeft.getX() + w, upperLeft.getY() + h);
    }
    /**
     * creates new line that represents the left rectangle's line.
     * @return left line of the rectangle
     */
    public Line getLeftLine() {
        double h = this.getHeight();
        return new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + h);
    }
}