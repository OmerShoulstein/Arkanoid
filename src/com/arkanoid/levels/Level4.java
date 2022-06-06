package com.arkanoid.levels;

import com.arkanoid.geometry.Point;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.gui.Block;
import com.arkanoid.gui.Sprite;
import com.arkanoid.logic.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Omer Shoulstein
 */
public class Level4 implements LevelInformation {
    private int width = 800, height = 600;

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity[] velArr = {new Velocity(3, 4), new Velocity(0, 5), new Velocity(-3, 4)};
        return Arrays.asList(velArr);
    }

    @Override
    public int paddleSpeed() {
        return width / 60;
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
        return "The Hard Level";
    }

    @Override
    public Sprite getBackground() {
        return new Level4Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.white, Color.pink, Color.cyan, Color.green, Color.orange
                          , Color.BLUE, Color.RED, Color.YELLOW
                         };
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 3; j++) {
                blocks.add(new Block(new Rectangle(new Point(i * width / 16.0,
                                                   height / 3.0 + j * height / 20.0), width / 16.0,
                                                   height / 20.0, colors[i / 2])));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 48;
    }

    @Override
    public List<Point> ballStartingPositions() {
        Point[] pointArr = new Point[this.numberOfBalls()];
        for (int i = 0; i < this.numberOfBalls(); i++) {
            pointArr[i] = new Point((i + 1) * width / 4.0, 3 * height / 4.0);
        }
        return Arrays.asList(pointArr);
    }

    @Override
    public int getRadius() {
        return 5;
    }
}
