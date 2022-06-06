package com.arkanoid.logic;

import com.arkanoid.geometry.Point;

/**
 * @author Omer Shoulstein
 */
public class CollisionInfo {
    private final Point point;
    private final Collidable collidable;

    /**
     * Construct using a point and a collidable.
     *
     * @param point      the collision point
     * @param collidable the collidable
     */
    public CollisionInfo(Point point, Collidable collidable) {
        this.point = point;
        this.collidable = collidable;
    }

    /**
     * Return the collision point.
     *
     * @return the point where the collision occurs
     */
    public Point collisionPoint() {
        return this.point;

    }


    /**
     * Return the collidable.
     *
     * @return the collidable the caused the collision
     */
    public Collidable collisionObject() {
        return this.collidable;

    }
}