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
public class Level3 implements LevelInformation {
    private int width = 800, height = 600, radius = 5;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity[] velArr = {new Velocity(3, 4), new Velocity(-3, 4)};
        return Arrays.asList(velArr);
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
        return "More Layers";
    }

    @Override
    public Sprite getBackground() {
        return new Level3Background();
    }

    @Override
    public List<Block> blocks() {
        //Set the number of blocks
        int rows = 6, minCol = 7;
        List<Block> res = new ArrayList<>();
        //Set the height and width of the blocks
        int blockWidth = width / (minCol * 2), blockHeight = height / (20);
        Color[] colors = {Color.GREEN, Color.pink, Color.red, Color.yellow, Color.BLUE, Color.orange};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < minCol + i; j++) {
                //Create a block in the coordinates and the color and add it to the game
                res.add(new Block(new Rectangle(new Point(width - (j + 1) * blockWidth
                                                , height / 2.0 - i * blockHeight), blockWidth, blockHeight, colors[i])));
            }
        }
        return res;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 42;
    }

    @Override
    public List<Point> ballStartingPositions() {
        Point[] pointArr = new Point[this.numberOfBalls()];
        for (int i = 0; i < this.numberOfBalls(); i++) {
            pointArr[i] = new Point(width / 10.0 + (i + 1) * width / 3.0, 3 * height / 4.0);
        }
        return Arrays.asList(pointArr);
    }

    @Override
    public int getRadius() {
        return 5;
    }
}
