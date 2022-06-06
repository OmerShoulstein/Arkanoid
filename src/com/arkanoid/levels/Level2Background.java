package com.arkanoid.levels;

import biuoop.DrawSurface;
import com.arkanoid.gui.Sprite;

import java.awt.Color;

/**
 * @author Omer Shoulstein
 */
public class Level2Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.green);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.fillRectangle(250, 350, 50, 150);
        d.fillRectangle(500, 350, 50, 150);
        d.fillRectangle(250, 350, 300, 100);
        d.fillRectangle(325, 300, 150, 50);
        d.fillRectangle(250, 225, 75, 75);
        d.fillRectangle(475, 225, 75, 75);
    }

    @Override
    public void timePassed() {

    }
}
