package com.arkanoid.logic;

/**
 * @author Omer Shoulstein
 */
public interface HitNotifier {
    /**
     * Add a listener to the notifier.
     *
     * @param hl the listener that is being added
     */
    void addHitListener(HitListener hl);

    /**
     * Remove a listener to the notifier.
     *
     * @param hl the listener that is removed
     */
    void removeHitListener(HitListener hl);
}