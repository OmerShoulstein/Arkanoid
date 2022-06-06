package com.arkanoid.logic;

import com.arkanoid.gui.Ball;
import com.arkanoid.gui.Block;

/**
 * @author Omer Shoulstein
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Construct using a game and a counter.
     *
     * @param gameLevel          the game
     * @param removedBlocks the number of the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Notify the listeners that a hit occurred.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that caused the hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}