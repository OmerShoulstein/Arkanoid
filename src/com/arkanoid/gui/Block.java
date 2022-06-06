package com.arkanoid.gui;

import biuoop.DrawSurface;
import com.arkanoid.geometry.Point;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.logic.Collidable;
import com.arkanoid.logic.GameLevel;
import com.arkanoid.logic.HitListener;
import com.arkanoid.logic.HitNotifier;
import com.arkanoid.logic.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Shoulstein
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private final List<HitListener> hitListeners = new ArrayList<>();

    /**
     * construct using a rectangle.
     *
     * @param r the rectangle of the block
     */
    public Block(Rectangle r) {
        this.rectangle = r;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        Velocity res = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        if (collisionPoint.isOnLine(this.rectangle.getLeftVertical())
                || collisionPoint.isOnLine(this.rectangle.getRightVertical())) {
            res.setDx(-currentVelocity.getDx());
        }
        if (collisionPoint.isOnLine(this.rectangle.getLowerHorizontal())
                || collisionPoint.isOnLine(this.rectangle.getUpperHorizontal())) {

            res.setDy(-currentVelocity.getDy());
        }
        return res;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * add the block to the gameEnvironment and to the spritesCollection of the game.
     *
     * @param gameLevel the game to add the block to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Remove the block from the game.
     *
     * @param gameLevel the game to remove the block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify the listeners that a hit occurred.
     *
     * @param hitter the ball that caused the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
