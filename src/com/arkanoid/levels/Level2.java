package com.arkanoid.levels;

import com.arkanoid.geometry.Point;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.gui.Block;
import com.arkanoid.gui.Sprite;
import com.arkanoid.logic.Velocity;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

/**
 * @author Omer Shoulstein
 */
public class Level2 implements LevelInformation {
    private int width = 800, height = 600, radius = 5;

    @Override
    public int numberOfBalls() {
        return 5;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity[] velArr = {new Velocity(3, 4), new Velocity(3, 4)
            , new Velocity(0, 5), new Velocity(-3, 4), new Velocity(-3, 4)
        };
        return Arrays.asList(velArr);
    }

    @Override
    public int paddleSpeed() {
        return width / 200;
    }

    @Override
    public int paddleWidth() {
        return 3 * width / 4;
    }

    @Override
    public int paddleHeight() {
        return height / 60;
    }

    @Override
    public String levelName() {
        return "Fun Balls";
    }

    @Override
    public Sprite getBackground() {
        return new Level2Background();
    }

    @Override
    public List<Block> blocks() {
        int numOfBlocks = this.numberOfBlocksToRemove();
        Block[] blocks = new Block[this.numberOfBlocksToRemove()];
        Color[] colors = {Color.white, Color.pink, Color.cyan, Color.green, Color.orange
                          , Color.BLUE, Color.RED, Color.YELLOW
                         };
        for (int i = 0; i < numOfBlocks; i++) {
            blocks[i] = new Block(new Rectangle(new Point(i * width / (double) numOfBlocks,
                                                height / 4.0), width / 16.0, height / 20.0, colors[i / 2]));
        }
        return Arrays.asList(blocks);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 16;
    }

    @Override
    public List<Point> ballStartingPositions() {
        Point[] pointArr = new Point[this.numberOfBalls()];
        for (int i = 0; i < this.numberOfBalls(); i++) {
            pointArr[i] = new Point((i + 1) * width / 6.0, 3 * height / 4.0);
        }
        return Arrays.asList(pointArr);
    }

    @Override
    public int getRadius() {
        return 5;
    }
}
