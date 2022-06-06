package com.arkanoid.geometry;

/**
 * @author Omer Shoulstein
 */
public class Point {
    //The variables of com.arkanoid.geometry.Point
    private final double x;
    private final double y;

    /**
     * Constructor.
     *
     * @param x the x value
     * @param y the y value
     */
    public Point(double x, double y) {
        this.y = y;
        this.x = x;
    }

    /**
     * Return the distance of this point to the other point.
     *
     * @param other the second point
     * @return the distance
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * Check if another com.arkanoid.geometry.Point is equal to this.
     *
     * @param other the other com.arkanoid.geometry.Point
     * @return true if equal false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double epsilon = 15E-10;
        return (Math.abs(this.x - other.getX()) <= epsilon && Math.abs(this.y - other.getY()) <= epsilon);
    }


    /**
     * Get the x value.
     *
     * @return the x value of this com.arkanoid.geometry.Point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get the y value.
     *
     * @return the y value of this com.arkanoid.geometry.Point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Return true if the point is on the line and false otherwise.
     *
     * @param line the line to check
     * @return true if the point is on the line and false otherwise
     */
    public boolean isOnLine(Line line) {
        double epsilon = 15E-10;
        //Check if the line is parallel to the y axis
        if (line.start().x == line.end().x) {
            //Return if the point is between the ends of the line and has the same x value
            Point[] upperLower = line.getUpperLower();
            return this.y < upperLower[0].y && this.y > upperLower[1].y && Math.abs(this.x - line.start().x) < epsilon;
        }
        //Calculate the equation of the line
        double slope = (line.start().y - line.end().y) / (line.start().x - line.end().x);
        double constant = line.start().y - line.start().x * slope;
        //Return if the point is on the line using the equation
        return Math.abs(this.y - slope * this.x - constant) <= epsilon;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
