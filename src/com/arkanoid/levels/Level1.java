package com.arkanoid.levels;

import com.arkanoid.geometry.Point;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.gui.Block;
import com.arkanoid.gui.Sprite;
import com.arkanoid.logic.Velocity;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

/**
 * @author Omer Shoulstein
 */
public class Level1 implements LevelInformation {
    private int width = 800, height = 600, radius = 5;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return Collections.singletonList(new Velocity(0, 5));
    }

    @Override
    public int paddleSpeed() {
        return width / 80;
    }

    @Override
    public int paddleWidth() {
        return width / 4;
    }

    @Override
    public int paddleHeight() {
        return height / 60;
    }


    @Override
    public String levelName() {
        return "Easy Level";
    }

    @Override
    public Sprite getBackground() {
        return new Level1Background();
    }

    @Override
    public List<Block> blocks() {
        return Collections.singletonList(new Block(new Rectangle(
                                             new Point(width / 2.0 - width / 30.0, height / 3.0 - height / 25.0),
                                             width / 15.0, height / 23.0, Color.BLACK)));
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public List<Point> ballStartingPositions() {
        return Collections.singletonList(new Point(width / 2.0 - radius / 2.0, 3 * height / 4.0));
    }

    @Override
    public int getRadius() {
        return this.radius;
    }
}
