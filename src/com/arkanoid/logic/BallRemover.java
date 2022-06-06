package com.arkanoid.logic;

import com.arkanoid.gui.Ball;
import com.arkanoid.gui.Block;

/**
 * @author Omer Shoulstein
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Construct using a game and a counter.
     *
     * @param gameLevel    the game to add the remover to
     * @param counter the counter of the balls in the game
     */
    public BallRemover(GameLevel gameLevel, Counter counter) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = counter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        this.remainingBlocks.decrease(1);
    }
}
