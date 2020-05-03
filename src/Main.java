import gameplay.logic.Game;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        game.initialize(new ArrayList<>(List.of("Eric","Bri","Adam")));
    }
}
