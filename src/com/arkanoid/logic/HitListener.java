package com.arkanoid.logic;

import com.arkanoid.gui.Ball;
import com.arkanoid.gui.Block;

/**
 * @author Omer Shoulstein
 */
public interface HitListener {
    /**
     * Notify that a hit occurred.
     *
     * @param beingHit that block that was hit
     * @param hitter   the ball that caused the hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}