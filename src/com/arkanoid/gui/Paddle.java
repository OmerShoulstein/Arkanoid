package com.arkanoid.gui;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import com.arkanoid.geometry.Line;
import com.arkanoid.geometry.Point;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.logic.Collidable;
import com.arkanoid.logic.GameLevel;
import com.arkanoid.logic.Velocity;

/**
 * @author Omer Shoulstein
 */
public class Paddle implements Sprite, Collidable {

    private final biuoop.KeyboardSensor keyboard;
    private final int screenWidth;
    private Rectangle rectangle;
    private int speed;

    /**
     * Construct using a rectangle, a keyboard and the screen width.
     *
     * @param r           The rectangle
     * @param keyboard    the keyboard
     * @param screenWidth the width of the screen
     * @param speed       the speed of the paddle
     */
    public Paddle(Rectangle r, KeyboardSensor keyboard, int screenWidth, int speed) {
        this.rectangle = r;
        this.keyboard = keyboard;
        this.screenWidth = screenWidth;
        this.speed = speed;
    }

    /**
     * Move the paddle left if possible.
     */
    public void moveLeft() {
        //Check if the paddle intersects the left part of the screen
        if (this.rectangle.getUpperLeft().getX() - speed < 0) {
            return;
        }
        //Move the paddle
        Point newUpperLeft = new Point(this.rectangle.getUpperLeft().getX() - speed
                                       , this.rectangle.getUpperLeft().getY());
        this.rectangle = new Rectangle(newUpperLeft, this.rectangle.getWidth(), this.rectangle.getHeight()
                                       , this.rectangle.getColor());
    }

    /**
     * Move the paddle right if possible.
     */
    public void moveRight() {
        //Check if the paddle intersects the right part of the screen
        if (this.rectangle.getUpperLeft().getX() + speed + this.rectangle.getWidth() > screenWidth) {
            return;
        }
        //Move the paddle
        Point newUpperLeft = new Point(this.rectangle.getUpperLeft().getX() + speed
                                       , this.rectangle.getUpperLeft().getY());
        this.rectangle = new Rectangle(newUpperLeft, this.rectangle.getWidth(), this.rectangle.getHeight()
                                       , this.rectangle.getColor());
    }

    /**
     * Notify the paddle that time passed.
     */
    public void timePassed() {
        //Check which key was pressed and move accordingly
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw the paddle on a draw surface.
     *
     * @param d the draw surface
     */
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
    }

    /**
     * Return the collision rectangle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Calculate the velocity of an object that hit the paddle.
     *
     * @param hitter          the ball that caused the hit
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //Get the upper side of the rectangle
        Line upSide = this.rectangle.getUpperHorizontal();
        double x = collisionPoint.getX();
        //Calculate the size of the velocity
        double size = currentVelocity.getDx() * currentVelocity.getDx()
                      + currentVelocity.getDy() * currentVelocity.getDy();
        size = Math.sqrt(size);
        //Check the region of the collision and return the matching velocity
        if (x <= upSide.start().getX() + upSide.length() / 5) {
            return Velocity.fromAngleAndSpeed(300, size);
        }
        if (x <= upSide.start().getX() + 2 * upSide.length() / 5) {
            return Velocity.fromAngleAndSpeed(330, size);
        }
        if (x <= upSide.start().getX() + 3 * upSide.length() / 5) {
            return Velocity.fromAngleAndSpeed(0, size);
        }
        if (x <= upSide.start().getX() + 4 * upSide.length() / 5) {
            return Velocity.fromAngleAndSpeed(30, size);
        }
        return Velocity.fromAngleAndSpeed(60, size);
    }

    /**
     * Add the paddle to a game.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}