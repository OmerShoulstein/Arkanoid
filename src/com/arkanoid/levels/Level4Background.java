package com.arkanoid.levels;

import biuoop.DrawSurface;
import com.arkanoid.gui.Sprite;

import java.awt.Color;

/**
 * @author Omer Shoulstein
 */
public class Level4Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, 800, 600);

    }

    @Override
    public void timePassed() {

    }
}
