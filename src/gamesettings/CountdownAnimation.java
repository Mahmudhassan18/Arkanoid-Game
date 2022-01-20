package gamesettings;
import animation.Animation;
import sprites.SpriteCollection;
import levels.LevelInformation;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 15.6.2021
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection sprites;
    private LevelInformation levelInfo;
    private boolean done = false;
    private boolean firstFrame = true;
    private long millisecond;

    /**
     * CountdownAnimation constructor.
     * @param numOfSeconds num of seconds for every num
     * @param countFrom number to count from
     * @param sprites sprites of the level to draw on screen
     * @param levelInfo info about the level
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection sprites, LevelInformation levelInfo) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.sprites = sprites;
        this.levelInfo = levelInfo;
    }

    /**
     * returns if animation should stop.
     * @return boolena if animation is done or not
     */
    public boolean shouldStop() {
        return this.done;
    }

    /**
     * count down animation do one frame.
     * @param d draw surface given
     */
    public void doOneFrame(DrawSurface d) {
        //checks if it's the first frame
        if (this.firstFrame) {
            // gets the milliseconds
            this.millisecond = System.currentTimeMillis();
            //change to not first frame
            this.firstFrame = false;
        }
        // draws every sprite on drawing surface
        this.sprites.drawAllOn(d);
        //gets current milliseconds
        long currentMill = System.currentTimeMillis();
        //gets how many seconds to show every number
        double singleCountLength = this.numOfSeconds / (double) this.countFrom;
        //number to draw on screen
        int numberToDraw = (int) ((double) (1 + this.countFrom) - (double) (currentMill - this.millisecond)
                / (singleCountLength * 1000.0D));
        d.setColor(Color.BLACK);
        // draws the number
        d.drawText(400, 300, "" + numberToDraw, 50);
        //checks if every number is drawn and if so changes done
        if ((double) (currentMill - this.millisecond) > this.numOfSeconds * 1000.0D) {
            this.done = true;
        }

    }
}
