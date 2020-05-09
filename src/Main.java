import gameplay.logic.Game;
import gameplay.logic.event.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        game.initialize(new ArrayList<>(List.of("Eric","Bri","Adam")));
        game.initializeNextLevel();
        game.startLevel();

        while(game.isRunning()){
            GameEvent e = game.pollEventPump();
            if (e != null) System.out.println(e.getEventType().toString());
        }



    }
}
