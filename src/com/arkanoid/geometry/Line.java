package com.arkanoid.geometry;

import java.util.ArrayList;

/**
 * @author Omer Shoulstein
 */
public class Line {
    private final Point start;
    private final Point end;


    /**
     * Construct using 2 points.
     *
     * @param start the starting point
     * @param end   the ending point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Construct using 4 values.
     *
     * @param x1 the x value of the starting point
     * @param y1 the y value of the starting point
     * @param x2 the x value of the ending point
     * @param y2 the y value of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line
     */
    public double length() {
        //Calculate the length of the line using the distance function
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        //Calculate the middle using average values
        return new Point((this.start.getX() + this.end.getX()) / 2.0,
                         (this.start.getY() + this.end.getY()) / 2.0);
    }

    /**
     * @return the starting point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the ending point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Gets another line and returns it intersects this line.
     *
     * @param other other line
     * @return true if intersecting false otherwise
     */
    public boolean isIntersecting(Line other) {
        //Check if there is one point of intersection using the other function
        if (this.intersectionWith(other) != null) {
            return true;
        }
        //Calculate the slopes and constants of the lines
        double slope1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double slope2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());

        double constant1 = this.end.getY() - slope1 * this.end.getX();
        double constant2 = other.end.getY() - slope2 * other.end.getX();

        //Sort the points by X axis
        Point thisUpper = this.getUpperLower()[0], thisLower = this.getUpperLower()[1],
              otherUpper = other.getUpperLower()[0], otherLower = other.getUpperLower()[1];

        //Check if the lines are equal and parallel to the Y axis
        if ((this.start.getX() == this.end.getX()
                && this.end.getX() == other.start.getX() && other.end.getX() == other.start.getX()
                && ((thisLower.getY() > otherLower.getY() && thisLower.getY() < otherUpper.getY())
                    || (thisUpper.getY() < otherUpper.getY() && thisUpper.getY() > otherLower.getY())))) {
            return true;
        }
        double epsilon = 15E-14;
        //Check if the lines are equal and not parallel to the Y axis
        return Math.abs(slope1 - slope2) < epsilon && Math.abs(constant1 - constant2) <= epsilon
               && ((thisUpper.getX() >= otherLower.getX() && thisUpper.getX() <= otherUpper.getX())
                   || (otherUpper.getX() >= thisLower.getX() && otherUpper.getX() <= thisUpper.getX()));

    }

    /**
     * Returns the intersection point if it exists and null otherwise.
     *
     * @param other the line to check intersection with
     * @return the intersection point
     */
    public Point intersectionWith(Line other) {
        //Check if the edge points are the intersection points
        if (this.getUpperLower()[1].equals(other.getUpperLower()[0])) {
            return this.getUpperLower()[1];
        }
        if (this.getUpperLower()[0].equals(other.getUpperLower()[1])) {
            return this.getUpperLower()[0];
        }
        //Get the slopes of the lines
        double slope1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double slope2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());

        //Check if the lines are parallel or equal
        if (slope1 == slope2) {
            return null;
        }

        //Check if the lines are parallel to each other and to the Y axis
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
            return null;
        }

        //Get the constants of the equations
        double constant1 = this.end.getY() - slope1 * this.end.getX();
        double constant2 = other.end.getY() - slope2 * other.end.getX();
        double intersectionX;
        double intersectionY;

        //Check if on of the lines is parallel to the Y axis
        if (this.start.getX() == this.end.getX()) {
            //Calculate the X and Y values
            intersectionX = this.start.getX();
            intersectionY = slope2 * intersectionX + constant2;
            //Check if the y value is in the range of the vertical line
            if ((intersectionY > this.start.getY() && intersectionY > this.end.getY())
                    || (intersectionY < this.start.getY() && intersectionY < this.end.getY())) {
                return null;
            }
        } else if (other.start.getX() == other.end.getX()) {
            //Calculate the X and Y values
            intersectionX = other.start.getX();
            intersectionY = slope1 * intersectionX + constant1;
            //Check if the y value is in the range of the vertical line
            if ((intersectionY > other.start.getY() && intersectionY > other.end.getY())
                    || (intersectionY < other.start.getY() && intersectionY < other.end.getY())) {
                return null;
            }
        } else {
            //Calculate the X and Y values if the lines are not parallel to the Y axis
            intersectionX = (constant2 - constant1) / (slope1 - slope2);
            intersectionY = slope2 * intersectionX + constant2;
        }

        //Sort the points by X axis
        Point thisUpper = this.getUpperLower()[0], thisLower = this.getUpperLower()[1],
              otherUpper = other.getUpperLower()[0], otherLower = other.getUpperLower()[1];

        //Check if the intersection is in the range of the points
        if (intersectionX > thisUpper.getX() || intersectionX < thisLower.getX()
                || intersectionX > otherUpper.getX() || intersectionX < otherLower.getX()) {
            return null;
        }
        Point res = new Point(intersectionX, intersectionY);
        //Return the point
        return new Point(intersectionX, intersectionY);
    }

    /**
     * Returns true if the lines are equal and false otherwise.
     *
     * @param other the line to compare with
     * @return true if equal false otherwise
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        //Check if the lines are equal and return the result
        return this.start.equals(other.start) && this.end.equals(other.end);
    }


    /**
     * Returns an array with the point with the bigger X value on index 0 and the other point on index 1.
     *
     * @return The array with the sorted points
     */
    public Point[] getUpperLower() {
        //Declare the array
        Point[] points = new Point[2];
        //Check which X value is greater, if they are equal, check which Y value is greater
        if (this.end.getX() > this.start.getX()) {
            //Set the elements of the array
            points[0] = this.end;
            points[1] = this.start;
        } else if (this.end.getX() < this.start.getX()) {
            //Set the elements of the array
            points[0] = this.start;
            points[1] = this.end;
        } else if (this.end.getY() > this.start.getY()) {
            //Set the elements of the array
            points[0] = this.end;
            points[1] = this.start;
        } else {
            //Set the elements of the array
            points[1] = this.end;
            points[0] = this.start;
        }
        //Return the array
        return points;
    }

    /**
     * Return the closest intersection point to the start of the line, if it doesn't exist, return null.
     *
     * @param rect The rectangle
     * @return the intersection points
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //Set a list of intersections
        ArrayList<Point> intersections = (ArrayList<Point>) rect.intersectionPoints(this);
        //If no intersections were found, return null
        if (intersections.size() == 0) {
            return null;
        }
        //Search the closest point to the start of the line
        Point closest = intersections.get(0);
        for (int i = 1; i < intersections.size(); i++) {
            //If the point is closer than the closest point, change the closest point
            if (intersections.get(i).distance(this.start) < closest.distance(this.start)) {
                closest = intersections.get(i);
            }
        }
        //Return the closest point
        return closest;
    }

    /**
     * Move the line by size.
     *
     * @param size the size of the movement
     * @return the moved line
     */
    public Line moveBy(double size) {
        //Calculate the new start and end values
        double extendedEndX = ((this.length() + size) * this.end.getX() - size * this.start.getX()) / this.length();
        double extendedEndY = ((this.length() + size) * this.end.getY() - size * this.start.getY()) / this.length();
        double extendedStartX = ((this.end.getX() * size) + (this.length() - size) * this.start.getX()) / this.length();
        double extendedStartY = ((this.end.getY() * size) + (this.length() - size) * this.start.getY()) / this.length();
        //Return the new line
        return new Line(new Point(extendedStartX, extendedStartY), new Point(extendedEndX, extendedEndY));
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}