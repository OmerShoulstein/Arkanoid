package com.arkanoid.logic;

/**
 * @author Omer Shoulstein
 */
public class Counter {
    private int counter;

    /**
     * Construct using an int.
     *
     * @param number the starting number of the counter
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * Add to the current count.
     *
     * @param number the amount to add
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtract from the current count.
     *
     * @param number the number to subtract
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * @return The value of the counter
     */
    public int getValue() {
        return this.counter;
    }
}