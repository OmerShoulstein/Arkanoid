package com.arkanoid.logic;

import com.arkanoid.geometry.Point;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.gui.Ball;

/**
 * @author Omer Shoulstein
 */
public interface Collidable {
    /**
     * @return the rectangle of the collidable
     */
    Rectangle getCollisionRectangle();

    /**
     * notify the object that there was a hit and return the new velocity.
     *
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the ball
     * @param hitter          the ball that caused the hit
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}