package com.arkanoid.levels;

import biuoop.DrawSurface;
import com.arkanoid.logic.Animation;

/**
 * @author Omer Shoulstein
 */
public class EndScreen implements Animation {
    private boolean won;
    private int score;

    /**
     * Construct using the winning state and the score.
     * @param won the winning state
     * @param score the score of the player
     */
    public EndScreen(boolean won, int score) {
        this.won = won;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.won) {
            d.drawText(10, d.getHeight() / 2, "You win! your score is " + score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game over. your score is " + score, 32);

        }
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}

