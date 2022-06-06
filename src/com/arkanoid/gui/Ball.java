package com.arkanoid.gui;

import biuoop.DrawSurface;
import com.arkanoid.geometry.Line;
import com.arkanoid.geometry.Point;
import com.arkanoid.logic.CollisionInfo;
import com.arkanoid.logic.GameEnvironment;
import com.arkanoid.logic.GameLevel;
import com.arkanoid.logic.HitListener;
import com.arkanoid.logic.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Shoulstein
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private final Color color;
    private Velocity velocity;
    private final GameEnvironment gameEnvironment;
    private final List<HitListener> hitListeners = new ArrayList<>();


    /**
     * Construct using a center point, a radius and a color.
     *
     * @param center the center
     * @param r      the radius
     * @param color  the color
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = null;
    }

    /**
     * Construct a ball with the given center point, radius and game environment.
     *
     * @param center          The center of the ball
     * @param r               the radius of the ball
     * @param gameEnvironment the com.arkanoid.logic.GameEnvironment that the ball needs to stay in
     * @param color           the color of the ball
     */
    public Ball(Point center, int r, GameEnvironment gameEnvironment, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Construct a ball with center (x,y), radius r and game environment.
     *
     * @param x               the X value of the center
     * @param y               the Y value of the center
     * @param r               the radius of the ball
     * @param gameEnvironment the com.arkanoid.logic.GameEnvironment that the ball needs to stay in
     */
    public Ball(double x, double y, int r, GameEnvironment gameEnvironment) {
        //Set and construct the point
        this.center = new Point(x, y);
        this.r = r;
        this.color = Color.BLACK;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * @return the X value
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the Y value
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return the gameEnvironment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Draws the ball on the given surface.
     *
     * @param surface the surface to draw on
     **/
    public void drawOn(DrawSurface surface) {
        //Set the color to the color of the ball
        surface.setColor(this.getColor());
        //Draw the ball
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Set the x and y velocities of the ball.
     *
     * @param dx the new x velocity
     * @param dy the new y velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Set the velocity of the ball.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Moves the ball and changes the velocity if detects a collidable.
     */
    public void moveOneStep() {
        //Calculate the trajectory of the ball
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        //Move the trajectory by the radius of the ball
        trajectory = trajectory.moveBy(this.r);
        //Get the closest collision to the ball
        CollisionInfo nearestCollision = this.gameEnvironment.getClosestCollision(trajectory);
        if (nearestCollision == null) {
            //Move the ball according to the speed
            this.center = this.velocity.applyToPoint(this.center);
            return;
        }
        //Change the velocity
        this.velocity = nearestCollision.collisionObject().hit(this, nearestCollision.collisionPoint(), this.velocity);
        //Move the ball so it touches the border
        Line centerToCollision = new Line(this.center, nearestCollision.collisionPoint()).moveBy(-this.r * 1.1);
        this.center = centerToCollision.end();
    }

    /**
     * add the ball to the sprite collection of a given game.
     *
     * @param gameLevel the game to add the ball to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Removes the ball from the sprites collection of a game.
     *
     * @param gameLevel the game to remove the ball from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }


}