package animation;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 15.6.2021
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    /**
     * Animation runner constructor.
     * @param gui graphic user interface given.
     * @param fps Frames per seconds.
     */
    public AnimationRunner(GUI gui, int fps) {
        this.framesPerSecond = fps;
        this.gui = gui;
    }

    /**
     * runs the animation by doing one frames on drawsurface.
     * @param animation animation to run
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        //if blocks or balls in games less than 0 exits loop
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}


