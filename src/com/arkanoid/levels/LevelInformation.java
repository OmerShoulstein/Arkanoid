package com.arkanoid.levels;

import com.arkanoid.geometry.Point;
import com.arkanoid.gui.Block;
import com.arkanoid.gui.Sprite;
import com.arkanoid.logic.Velocity;

import java.util.List;

/**
 * @author Omer Shoulstein
 */
public interface LevelInformation {
    /**
     * @return the number of balls in the levels.
     */
    int numberOfBalls();

    /**
     * @return the initial velocities of the balls
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the ball
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle
     */
    int paddleWidth();

    /**
     * @return the height of the paddle
     */
    int paddleHeight();

    /**
     * @return the name of the level
     */
    String levelName();

    /**
     * @return the background sprite of the level
     */
    Sprite getBackground();

    /**
     * @return the block layout of the level
     */
    List<Block> blocks();

    /**
     * @return the number of blocks to remove from the level
     */
    int numberOfBlocksToRemove();

    /**
     * @return the starting positions of the balls
     */
    List<Point> ballStartingPositions();

    /**
     * @return the radius of the balls
     */
    int getRadius();
}