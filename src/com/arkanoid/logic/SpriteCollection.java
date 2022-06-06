package com.arkanoid.logic;

import biuoop.DrawSurface;
import com.arkanoid.gui.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Shoulstein
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Construct the sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Remove a sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * @return the list of sprites.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Call timePassed on all of the sprites in the collection.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : new ArrayList<>(this.sprites)) {
            sprite.timePassed();
        }
    }

    /**
     * Call drawOn on all of the sprites in the collection.
     *
     * @param d the draw surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
}