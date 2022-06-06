package com.arkanoid.logic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Omer Shoulstein
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private String key;
    private KeyboardSensor sensor;
    private boolean stop;
    private boolean isAlreadyPressed = true;

    /**
     * Construct using a sensor, a key and an animation.
     * @param sensor the keyboard
     * @param key the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (sensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
