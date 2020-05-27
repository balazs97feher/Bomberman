package gameplay.logic.schedule;

import gameplay.logic.Controller;

import java.util.TimerTask;

/**
 * class to facilitate the scheduled moving of monsters
 */
public class HandleMonsters extends TimerTask {
    private Controller controller;

    public HandleMonsters(Controller c){
        controller = c;
    }

    private void notifyController(){
        controller.handleMonsters();
    }

    @Override
    public void run() {
        notifyController();
    }
}
