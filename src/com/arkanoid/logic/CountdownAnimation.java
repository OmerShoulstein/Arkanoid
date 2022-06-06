package com.arkanoid.logic;

import biuoop.DrawSurface;

/**
 * @author Omer Shoulstein
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int framesPerSecond;
    private double changeRate;
    private double currentTime = 0;
    private int currentCount;

    /**
     * Construct using the needed parameters.
     *
     * @param numOfSeconds    the number of seconds to display the animation
     * @param countFrom       the number to count from
     * @param gameScreen      the sprites to display
     * @param framesPerSecond the frames per seconds to run
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, int framesPerSecond) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.framesPerSecond = framesPerSecond;
        this.changeRate = numOfSeconds / (countFrom + 1);
        this.currentCount = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        if (currentCount != 0) {
            d.drawText(d.getWidth() / 2 - 64, d.getHeight() / 2, Integer.toString(currentCount), 64);
        } else {
            d.drawText(d.getWidth() / 2 - 64, d.getHeight() / 2, "GO!", 64);
        }
        currentTime += 1 / (double) framesPerSecond;
        if (currentTime > changeRate) {
            currentCount--;
            changeRate += numOfSeconds / (countFrom + 1);
        }
    }

    @Override
    public boolean shouldStop() {
        return currentTime > numOfSeconds;
    }
}
