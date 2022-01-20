package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 1.4.2021
 */
public class Line {
    // Starting point
    private final Point start;
    //Ending point
    private final Point end;

    /**
     * Line constructor from two points.
     * @param start staring point
     * @param end ending point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Line constructor from 2 x's and 2 y's.
     * @param x1 start x
     * @param y1 start y
     * @param x2 end x
     * @param y2 end y
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculates length of given line.
     * @return line length
     */
    public double length() {
        //Square root of ((x1-x2)^2 + (y1-y2)^2)
        double x = Math.pow(this.start.getX() - this.end.getX(), 2);
        double y = Math.pow(this.start.getY() - this.end.getY(), 2);
        return Math.sqrt(x + y);
    }

    /**
     * calculates middle point.
     * @return the middle point
     */
    public Point middle() {
        //Creates a new point that the x is (start.x+end.x)/2 and the y is (start.y+end.y)/2
        double x = (this.start().getX() + this.end().getX()) / 2;
        double y = (this.start().getY() + this.end().getY()) / 2;
        return new Point(x, y);
    }

    /**
     * @return the staring point of given line.
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * @return the end line of given line.
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * Returns the intersection point if the lines intersect.
     * and null otherwise
     * @param other other lines given
     * @return intersection points and if not found null
     */
    public Point intersectionWith(Line other) {
        double var1 = this.start.getX() - other.start.getX();
        double var2 = other.start.getY() - other.end.getY();
        double var3 = this.start.getY() - other.start.getY();
        double var4 = other.start.getX() - other.end.getX();
        double var5 = this.start.getX() - this.end.getX();
        double var6 = this.start.getY() - this.end.getY();
        double t = ((var1 * var2) - (var3 * var4)) / ((var5 * var2) - (var6 * var4));
        double xInter = this.start.getX() + t * (this.end.getX() - this.start.getX());
        double yInter = this.start.getY() + t * (this.end.getY() - this.start.getY());
        if (!this.isXInRange(xInter, other) || !this.isYInRange(yInter, other)) {
            return null;
        } else {
            return new Point(xInter, yInter);
        }
    }
    /**
     * checks if x received is in range of x's of line segment.
     * @param x that needs to be checked if in range
     * @param other other line given
     * @return true or false
     */
    public boolean isXInRange(double x, Line other) {
        //if x is bigger the the smallest x and smaller then biggest x of the two lines
        if (x >= Math.min(this.start.getX(), this.end.getX())) {
            if (x <= Math.max(this.start.getX(), this.end.getX())) {
                if (x >= Math.min(other.start.getX(), other.end.getX())) {
                    if (x <= Math.max(other.start.getX(), other.end.getX())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * checks if Y received is in range of Y's of line segment.
     * @param y that needs to be checked if in range
     * @param other other line given
     * @return true or false
     */
    public boolean isYInRange(double y, Line other) {
        //if y is bigger the the smallest y and smaller then biggest y of the two lines
        if (y >= Math.min(this.start.getY(), this.end.getY())) {
            if (y <= Math.max(this.start.getY(), this.end.getY())) {
                if (y >= Math.min(other.start.getY(), other.end.getY())) {
                    if (y <= Math.max(other.start.getY(), other.end.getY())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * checks if two lines is equal if x's and y's equal.
     * @param other other line
     * @return booleans
     */
    public boolean equals(Line other) {
        return (this.start == other.start && this.end == other.end);
    }

    /**
     * Get all intersections points with given rectangle.
     * @param rect given rec to check collision with
     * @return list of collision point possibly empty
     */
    public java.util.List<Point> getIntersectionPoints(Rectangle rect) {
        //4 intersections point with 4 lines (could be null)
        Point[] interPoints = new Point[4];
        List interList = new ArrayList();
        //the 4 lines of the rectangle
        Line line1 = rect.getUpperLine();
        Line line2 = rect.getBottomLine();
        Line line3 = rect.getRightLine();
        Line line4 = rect.getLeftLine();
        // 4 intersection points with the 4 lines
        interPoints[0] = this.intersectionWith(line1);
        interPoints[1] = this.intersectionWith(line2);
        interPoints[2] = this.intersectionWith(line3);
        interPoints[3] = this.intersectionWith(line4);
        //goes over every point if not null adds it to list
        for (int i = 0; i < 4; i++) {
            if (interPoints[i] != null) {
                interList.add(interPoints[i]);
            }
        }
        //returns list
        return interList;
    }

    /**
     * checks the closest point to the start of line .
     * @param list list of intersection points
     * @return closest point
     */
    public Point closestPointToStart(List<Point> list) {
        //starts with first point in list
        Point closest = list.get(0);
        //for loop to compare list[i] and list[i+1]
        for (int i = 0; i + 1 < list.size(); i++) {
            //goes over every condition possible and checks accordingly
            if (this.start.getX() == this.end.getX()) {
                if (closest.getY() > this.start.getY()) {
                    if (this.start.getY() - closest.getY() <= this.start.getY() - list.get(i + 1).getY()) {
                        closest = list.get(i + 1);
                    }
                } else {
                    if (this.start.getY() - closest.getY() >= this.start.getY() - list.get(i + 1).getY()) {
                        closest = list.get(i + 1);
                    }
                }
            }
            if (this.start.getY() == this.end.getY()) {
                if (closest.getX() > this.start.getX()) {
                    if (this.start.getX() - closest.getX() <= this.start.getX() - list.get(i + 1).getX()) {
                        closest = list.get(i + 1);
                    }
                } else {
                    if (this.start.getX() - closest.getX() >= this.start.getX() - list.get(i + 1).getX()) {
                        closest = list.get(i + 1);
                    }
                }
            } else {
                if (closest.getX() > this.start.getX()) {
                    if (this.start.getX() - closest.getX() <= this.start.getX() - list.get(i + 1).getX()) {
                        closest = list.get(i + 1);
                    }
                } else {
                    if (this.start.getX() - closest.getX() >= this.start.getX() - list.get(i + 1).getX()) {
                        closest = list.get(i + 1);
                    }
                }
            }
        }
        return closest;
    }
    /**
     * checks if line intersects with rectangle.
     * if not it returns null
     * @param rect rectangles to check
     * @return null or closest point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> interPoints = this.getIntersectionPoints(rect);
        if (interPoints.size() == 0) {
            return null;
        }
        Point closestPoint = this.closestPointToStart(interPoints);
        return closestPoint;
    }
}

