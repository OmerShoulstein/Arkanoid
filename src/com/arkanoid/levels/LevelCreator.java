package com.arkanoid.levels;

/**
 * @author Omer Shoulstein
 */
public class LevelCreator {
    /**
     * Return the level according to the input.
     * @param num the number of the level to create
     * @return the level
     */
    public LevelInformation returnLevel(int num) {
        if (num == 1) {
            return new Level1();
        }
        if (num == 2) {
            return new Level2();
        }
        if (num == 3) {
            return new Level3();
        }
        if (num == 4) {
            return new Level4();
        }
        return null;
    }
}
