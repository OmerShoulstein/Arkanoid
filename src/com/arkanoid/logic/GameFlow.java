package com.arkanoid.logic;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import com.arkanoid.levels.EndScreen;
import com.arkanoid.levels.LevelInformation;

import java.util.List;

/**
 * @author Omer Shoulstein
 */
public class GameFlow {
    private AnimationRunner runner;
    private GUI gui;

    /**
     * Construct using an animation runner and a gui.
     *
     * @param ar  the animation runner
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, GUI gui) {
        this.runner = ar;
        this.gui = gui;
    }

    /**
     * Run the levels by order.
     *
     * @param levels the levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter(0);
        boolean won = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(this.gui, 60, levelInfo, this.runner, score);
            level.initialize();
            level.run();
            if (level.getRemainingBalls() == 0) {
                won = false;
                break;
            }
        }
        runner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY
                   , new EndScreen(won, score.getValue())));
        gui.close();
    }
}
