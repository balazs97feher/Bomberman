package gameplay.logic.schedule;

import gameplay.grid.Bomb;
import gameplay.logic.Controller;

import java.util.TimerTask;

/**
 * class to facilitate the scheduled detonation of a bomb
 */
public class Detonation extends TimerTask {
    private Controller controller;
    private Bomb bomb;

    public Detonation(Controller c, Bomb b){
        controller = c;
        bomb = b;
    }

    private void notifyController(){
        controller.detonateBomb(bomb);
    }

    @Override
    public void run() {
        notifyController();
    }
}