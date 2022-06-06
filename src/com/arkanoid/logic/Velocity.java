package com.arkanoid.logic;

import com.arkanoid.geometry.Point;

/**
 * @author Omer Shoulstein
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Construct using two doubles.
     *
     * @param dx the x axis speed
     * @param dy the y axis speed
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Return a new velocity using an angle and a speed.
     *
     * @param angle the angle of the velocity
     * @param speed the size of the velocity
     * @return a calculated velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //Change the angle to radians in order to calculate the dx and dy
        angle = Math.toRadians(angle);
        double dx = Math.sin(angle) * speed;
        double dy = -Math.cos(angle) * speed;
        //Return the velocity
        return new Velocity(dx, dy);
    }

    /**
     * @return the x axis speed
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Set the x axis speed.
     *
     * @param newDx the new speed
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * @return the y axis speed
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Set the y axis speed.
     *
     * @param newDy the new speed
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * Apply the speed to a given point.
     *
     * @param p the point
     * @return a new point with changed axis
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}