package com.arkanoid.logic;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import com.arkanoid.geometry.Point;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.gui.Ball;
import com.arkanoid.gui.Block;
import com.arkanoid.gui.Paddle;
import com.arkanoid.gui.ScoreIndicator;
import com.arkanoid.gui.Sprite;
import com.arkanoid.gui.Text;
import com.arkanoid.levels.LevelInformation;

import java.awt.Color;

/**
 * @author Omer Shoulstein
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private Counter score;
    private Counter remainingBlocks = new Counter(0);
    private Counter remainingBalls = new Counter(0);
    private GUI gui;
    private int framesPerSecond;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation information;

    /**
     * Construct using a gui, frames per second, the information, the animation runner, the score counter.
     *
     * @param gui the gui
     * @param framesPerSecond the frames per second
     * @param information the level information
     * @param runner the animation runner
     * @param score the score
     */
    public GameLevel(GUI gui, int framesPerSecond, LevelInformation information,
                     AnimationRunner runner, Counter score) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.runner = runner;
        this.information = information;
        this.score = score;
    }

    /**
     * Add a collidable to the game.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add a sprite to the game.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a ball in the center point.
     *
     * @param center   the center point of the ball
     * @param color    the color of the ball
     * @param radius   the radius of the ball
     * @param velocity the velocity of the ball
     */
    public void initBall(Point center, Color color, int radius, Velocity velocity) {
        //Create the ball
        Ball ball = new Ball(center, radius, this.environment, color);
        //Set the velocity of the ball
        ball.setVelocity(velocity);
        //Add the ball to the game
        ball.addToGame(this);
        this.remainingBalls.increase(1);
    }

    /**
     * Initialize the borders of the game.
     *
     * @param radius the radius of the ball
     */
    public void initBorder(int radius) {
        //Create each border and add it to the game
        Block upBorder = new Block(new Rectangle(new Point(0, 0), gui.getDrawSurface().getWidth()
                                   , gui.getDrawSurface().getWidth() / 15.0 + 2 * radius, Color.BLACK));
        upBorder.addToGame(this);
        Block rightBorder = new Block(new Rectangle(new Point(0, 0), 0, gui.getDrawSurface().getHeight()
                                      , Color.BLACK));
        rightBorder.addToGame(this);
        Block leftBorder = new Block(new Rectangle(new Point(gui.getDrawSurface().getWidth(), 0),
                                     0, gui.getDrawSurface().getHeight(), Color.BLACK));
        leftBorder.addToGame(this);
    }

    /**
     * Initialize blocks for the game.
     *
     * @param remover          the blockRemover of the game
     * @param trackingListener the trackingListener of the game
     */
    public void initBlocks(BlockRemover remover, ScoreTrackingListener trackingListener) {
        for (Block block : information.blocks()) {
            block.addHitListener(remover);
            block.addHitListener(trackingListener);
            block.addToGame(this);
            this.remainingBlocks.increase(1);
        }
    }

    /**
     * Initialize a paddle.
     */
    public void initPaddle() {
        //Set width and height for the paddle
        int width = information.paddleWidth(), height = information.paddleHeight();
        //Set the upper left point of the ball
        Point upperLeft = new Point(this.gui.getDrawSurface().getWidth() / 2.0 - width / 2.0
                                    , this.gui.getDrawSurface().getHeight() - 3 * height);
        //Create the rectangle of the paddle
        Rectangle rectangle = new Rectangle(upperLeft, width, height, Color.BLACK);
        //Create the paddle and add it to the game
        new Paddle(rectangle, this.gui.getKeyboardSensor(), this.gui.getDrawSurface().getWidth()
                   , information.paddleSpeed()).addToGame(this);
    }

    /**
     * Initialize the paddle, the balls, the borders and the blocks.
     */
    public void initialize() {
        this.sprites.addSprite(this.information.getBackground());
        for (int i = 0; i < information.numberOfBalls(); i++) {
            initBall(information.ballStartingPositions().get(i), Color.red, information.getRadius()
                     , information.initialBallVelocities().get(i));
        }

        initPaddle();

        initBorder(information.getRadius());

        BlockRemover remover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        initBlocks(remover, scoreTrackingListener);

        Block deathRegion = new Block(new Rectangle(new Point(0
                                      , this.gui.getDrawSurface().getHeight() + 2 * information.getRadius())
                                      , this.gui.getDrawSurface().getWidth(), 0, Color.white));
        deathRegion.addToGame(this);

        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        deathRegion.addHitListener(ballRemover);

        ScoreIndicator indicator = new ScoreIndicator(this.score);
        this.addSprite(new Block(new Rectangle(new Point(0, 0), this.gui.getDrawSurface().getWidth()
                                               , this.gui.getDrawSurface().getHeight() / 10.0, Color.white)));
        this.addSprite(indicator);

        this.sprites.addSprite(new Text("Level Name: " + this.information.levelName(),
                                        this.gui.getDrawSurface().getWidth() / 10, this.gui.getDrawSurface().getHeight() / 15, 20));
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
        }
        if (this.remainingBlocks.getValue() == 0 || this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.gui.getKeyboardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.gui.getKeyboardSensor()
                            , KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

    }

    /**
     * Start the level.
     */
    public void start() {
        this.running = true;
    }

    /**
     * Run the game.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, framesPerSecond));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Remove a collidable from the game.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove a sprite from the game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * @return the amount of balls that remain
     */
    public int getRemainingBalls() {
        return remainingBalls.getValue();
    }

}