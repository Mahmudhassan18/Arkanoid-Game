package gamesettings;
import animation.Animation;
import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import levels.LevelInformation;
import counter.Counter;

import java.util.List;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 15.6.2021
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final biuoop.GUI gui;
    private final Counter score = new Counter();

    /**
     * game flow constructor.
     * @param animationRunner animation runner
     * @param keyboardSensor gui keyboard sensor
     * @param gui the gui
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor, GUI gui) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.gui = gui;
    }

    /**
     * levels runner takes list of levels and runs them.
     * @param levels list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        //game over animation
        Animation gameOver = new GameOver(score);
        //you win animation
        Animation youWin = new YouWin(score);
        //for loop of every level in levels list
        for (LevelInformation levelInfo : levels) {
            //creates levels based on level info in list
            GameLevel level = new GameLevel(levelInfo, animationRunner, keyboardSensor, score);
            //initializes the level
            level.initialize();
            //runs level while blocks and balls still there
            while (level.getRemainingBlocks() > 0 && level.getRemainingBalls() > 0) {
                level.run();
            }
            //checks if no more balls
            if (level.getRemainingBalls() == 0) {
                //runs you lose animation
                this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space", gameOver));
                //closes the gui
                this.gui.close();
            }
        }
        //if all levels passed runs you win animation
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space", youWin));
        //closes gui
        this.gui.close();
    }
}
