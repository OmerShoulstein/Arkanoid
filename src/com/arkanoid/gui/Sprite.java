package com.arkanoid.gui;

import biuoop.DrawSurface;

/**
 * @author Omer Shoulstein
 */
public interface Sprite {
    /**
     * Draw the sprite.
     *
     * @param d the draw surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}