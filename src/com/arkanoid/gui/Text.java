package com.arkanoid.gui;

import biuoop.DrawSurface;

/**
 * @author Omer Shoulstein
 */
public class Text implements Sprite {
    private String text;
    private int x, y, size;

    /**
     * Construct using a string, coordinates and size.
     * @param text the string to display
     * @param x the x value
     * @param y the y value
     * @param size the size of the text
     */
    public Text(String text, int x, int y, int size) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(x, y, text, size);
    }

    @Override
    public void timePassed() {

    }
}
