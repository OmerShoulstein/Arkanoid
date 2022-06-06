package com.arkanoid.geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Shoulstein
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private final Color color;
    private final Color outlineColor = Color.BLACK;

    /**
     * Create a rectangle with a point, width, height and color.
     *
     * @param upperLeft upper left point
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        this.color = color;
    }

    /**
     * Create a rectangle with a point, width and height.
     *
     * @param upperLeft upper left point
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        this.color = Color.BLACK;
    }

    /**
     * Return a list of intersections with a the line.
     *
     * @param line the line to check intersections with
     * @return the intersections
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //Declare the array of points
        List<Point> points = new ArrayList<>();
        //Get the sides of the rectangle
        Line[] sides = this.getSides();
        boolean foundDuplicate;
        //Loop over the sides
        for (Line side : sides) {
            //Check the intersection
            foundDuplicate = false;
            Point intersection = side.intersectionWith(line);
            if (intersection != null) {
                //Loop over the points
                for (Point point : points) {
                    //Check if the point is in the list
                    if (intersection.equals(point)) {
                        foundDuplicate = true;
                    }
                }
                //Add the point to the list
                if (!foundDuplicate) {
                    points.add(intersection);
                }
            }
        }
        //Return the list
        return points;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the up side of the rectangle
     */
    public Line getUpperHorizontal() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                        this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * @return the low side of the rectangle
     */
    public Line getLowerHorizontal() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                        this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * @return the right side of the rectangle
     */
    public Line getRightVertical() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                        this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }

    /**
     * @return the left side of the rectangle
     */
    public Line getLeftVertical() {
        return new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                        this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * @return the color of the rectangle
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the sides of the rectangle
     */
    public Line[] getSides() {
        Line[] outlines = new Line[4];
        outlines[0] = this.getUpperHorizontal();
        outlines[1] = this.getLowerHorizontal();
        outlines[2] = this.getLeftVertical();
        outlines[3] = this.getRightVertical();
        return outlines;
    }

    /**
     * Draw the rectangle on the draw surface.
     *
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        //Set the color to the color of the rectangle
        surface.setColor(this.getColor());
        //Draw the rectangle
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY()
                              , (int) this.width, (int) this.height);
        //Set the color to the color of the outline
        surface.setColor(this.outlineColor);
        //Draw the outline
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY()
                              , (int) this.width, (int) this.height);

    }

}