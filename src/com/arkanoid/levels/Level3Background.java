package com.arkanoid.levels;

import biuoop.DrawSurface;
import com.arkanoid.gui.Sprite;

import java.awt.Color;

/**
 * @author Omer Shoulstein
 */
public class Level3Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(42, 130, 21));
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(Color.black);
        d.fillRectangle(50, 400, 100, 300);
        d.fillRectangle(85, 350, 30, 50);
        d.fillRectangle(95, 250, 10, 150);
        d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                d.fillRectangle(55 + 20 * i, 560 - 50 * j, 10, 35);
            }
        }
        d.setColor(Color.red);
        d.fillCircle(100, 240, 10);
        d.setColor(Color.white);
        d.fillCircle(100, 240, 7);
        d.setColor(Color.red);
        d.fillCircle(100, 240, 3);

    }

    @Override
    public void timePassed() {

    }
}
