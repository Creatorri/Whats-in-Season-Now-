package input;

import java.awt.Component;
import seasonality.Crops;
import seasonality.Seasonality;

/**
 * listener for MButtons
 *
 * @author Torri
 */
public class MButtonInput {

    public String lastCommand;

    public void clicked(String command, Component parent) throws Exception {
        if (lastCommand == null ? command == null : lastCommand.equals(command)) {
            return;
        }
        System.out.println("MBUTTON:" + command);
        lastCommand = command;
        if (parent == Seasonality.mmp) {
            if (command.equalsIgnoreCase("Play Seasonality")) {
                Seasonality.mode = Seasonality.PLAY_MODE;
                Seasonality.mmp.setVisible(false);
                Seasonality.gp.setVisible(true);
                Seasonality.gp.startGame(1);
                Seasonality.resetPaint = true;
                Seasonality.score = 0;
                Seasonality.s.render();
                return;
            }
            if (command.equalsIgnoreCase("Learn")) {
                Seasonality.mode = Seasonality.LEARN_MODE;
                Seasonality.mmp.setVisible(false);
                Seasonality.gp.setVisible(true);
                Seasonality.gp.startGame(0);
                Seasonality.resetPaint = true;
                Seasonality.score = 0;
                Seasonality.s.render();
                return;
            }
            if (command.equalsIgnoreCase("Quit")) {
                Seasonality.s.dispose();
                System.exit(0);
                return;
            }
        }
        if (parent == Seasonality.gp) {
            if (command.equalsIgnoreCase("Back To Menu")) {
                Seasonality.gp.stopGame();
                Seasonality.s.render();
                return;
            }
            if (command.equalsIgnoreCase("Back")) {
                Seasonality.pickedup = -1;
                Seasonality.resetPaint = true;
                return;
            }
            for (int i = 0; i < Crops.values().length; i++) {
                if (Seasonality.pointTaken[i]) {
                    continue;
                }
                if (command.equalsIgnoreCase(Crops.values()[i].toString())) {
                    Seasonality.update = true;
                    if (Seasonality.mode == Seasonality.PLAY_MODE) {
                        Seasonality.pickedup = i;
                        break;
                    }
                    for (MButton m : Seasonality.buttons) {
                        if (m.name.equalsIgnoreCase("Pick Up") || m.name.equalsIgnoreCase("Put Back")) {
                            m.setVisible(true);
                        }
                    }
                    break;
                }
            }
            return;
        }
        if(parent == Seasonality.es){
            if (command.equalsIgnoreCase("Back To Menu")) {
                Seasonality.es.setVisible(false);
                Seasonality.mmp.setVisible(true);
                Seasonality.s.render();
                return;
            }
        }
        System.err.println("And this method (MButtonInput.clicked(String)) was called for what reason?\n Non fatal error in MButtonInput.class: No corresponding action to button press \"" + command + "\"");
    }

}
