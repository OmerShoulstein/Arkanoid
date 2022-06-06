package com.arkanoid.logic;

import com.arkanoid.geometry.Line;
import com.arkanoid.geometry.Point;

import java.util.ArrayList;

/**
 * @author Omer Shoulstein
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidables;

    /**
     * Construct and set an empty list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Add a collidable to the game environment.
     *
     * @param c the added collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove a collidable from the game environment.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Gets a line and returns the closest collision to its start.
     *
     * @param trajectory the movement trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //Declare variables for the point and the collidable
        Point closestPoint = null;
        Collidable closestCollidable = null;
        //Loop over the collidables
        for (Collidable collidable : collidables) {
            //Get the closest collision for the given collidable
            Point collision = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            //Check if the given point is the closest if it is not null
            if (collision != null) {
                //If points were not added, set the closest collision
                if (closestPoint == null) {
                    closestPoint = collision;
                    closestCollidable = collidable;
                } else {
                    //Check if the point is closer than the last closest point
                    if (collision.distance(trajectory.start()) < closestPoint.distance(trajectory.start())) {
                        //Set the collision point and collidable
                        closestPoint = collision;
                        closestCollidable = collidable;
                    }
                }
            }
        }
        //If no point was detected, return null
        if (closestPoint == null) {
            return null;
        }
        //Return the collision point and collidable
        return new CollisionInfo(closestPoint, closestCollidable);
    }


}