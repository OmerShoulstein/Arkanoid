package com.arkanoid.logic;

import com.arkanoid.gui.Ball;
import com.arkanoid.gui.Block;

/**
 * @author Omer Shoulstein
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Construct using a counter.
     *
     * @param scoreCounter the counter to set the tracker to
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }

    /**
     * @return the current score
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}