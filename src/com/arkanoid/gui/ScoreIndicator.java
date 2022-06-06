package com.arkanoid.gui;

import biuoop.DrawSurface;
import com.arkanoid.logic.Counter;

import java.awt.Color;

/**
 * @author Omer Shoulstein
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Construct using a counter.
     *
     * @param score the counter to set the indicator
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(4 * d.getWidth() / 9, d.getHeight() / 15, "Score: " + score.getValue(), 20);
    }

    @Override
    public void timePassed() {

    }
}
