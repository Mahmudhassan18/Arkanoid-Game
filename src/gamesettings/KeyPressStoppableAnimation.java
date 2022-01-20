package gamesettings;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Mahmud Hassan
 * @version 1
 * @since 15.6.2021
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop;

    /**
     * KeyPressStoppableAnimation constructor.
     * @param sensor keyboard sensor
     * @param key key to stop the animation
     * @param animation animation to run and stop
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        this.stop = sensor.isPressed(key);
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
