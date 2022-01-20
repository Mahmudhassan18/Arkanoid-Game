import animation.AnimationRunner;
import biuoop.GUI;
import gamesettings.GameFlow;
import levels.LevelInformation;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.WideEasy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahmud Hassan
 * @version 1
 * @since 22.4.2021
 */
public class Ass6Game {
    /**
     * Main function start that game.
     * @param args not used
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Bounce", 800, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui, 60);
        List<LevelInformation> levels = new ArrayList<>();
        //Creates new game
        GameFlow gameFlow = new GameFlow(runner, keyboard, gui);
        //initialize levels and runs them
        for (String arg: args){
            switch (arg) {
                case "1" -> levels.add(new DirectHit());
                case "2" -> levels.add(new WideEasy());
                case "3" -> levels.add(new Green3());
                case "4" -> levels.add(new FinalFour());
            }
        }
        if (levels.isEmpty()){
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }
        gameFlow.runLevels(levels);
    }
}
