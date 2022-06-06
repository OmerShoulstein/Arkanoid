package com.arkanoid.logic;

import biuoop.DrawSurface;

/**
 * @author Omer Shoulstein
 */
public interface Animation {
    /**
     * Play one frame of the animation.
     * @param d the draw surface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Check if the animation should stop.
     * @return if the animation should stop
     */
    boolean shouldStop();
}
