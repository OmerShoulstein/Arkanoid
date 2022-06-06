import biuoop.GUI;
import biuoop.Sleeper;
import com.arkanoid.levels.Level1;
import com.arkanoid.levels.Level2;
import com.arkanoid.levels.Level3;
import com.arkanoid.levels.Level4;
import com.arkanoid.levels.LevelCreator;
import com.arkanoid.levels.LevelInformation;
import com.arkanoid.logic.AnimationRunner;
import com.arkanoid.logic.GameFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Shoulstein
 */
public class Game {
    /**
     * The main program.
     *
     * @param args main parameters
     */
    public static void main(String[] args) {
        GUI gui = new GUI("arkanoid", 800, 600);
        GameFlow flow = new GameFlow(new AnimationRunner(gui, 60, new Sleeper()), gui);
        List<LevelInformation> levels = new ArrayList<>();
        LevelCreator creator = new LevelCreator();
        boolean isNum;
        for (String str : args) {
            isNum = true;
            for (char c : str.toCharArray()) {
                if (!Character.isDigit(c)) {
                    isNum = false;
                }
            }
            if (isNum) {
                int levelNum = Integer.parseInt(str);
                if (1 <= levelNum && 4 >= levelNum) {
                    levels.add(creator.returnLevel(levelNum));
                }
            }
        }
        if (levels.size() == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }
        flow.runLevels(levels);
    }
}
