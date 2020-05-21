import gameplay.LoggerMan;
import gameplay.logic.Game;
import gameplay.logic.event.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LoggerMan.initialize();

        Game game = new Game();
        game.initialize(new ArrayList<>(List.of("Eric","Bri","Adam")));
        game.initializeNextLevel();
        game.startLevel();

        game.placeBomb("Eric");
        game.placeBomb("Eric");
        game.placeBomb("Bri");
        while(game.isRunning()){
            GameEvent e = game.pollEventPump();
//            if (e != null) System.out.println(e.getEventType().toString());
        }



    }
}
