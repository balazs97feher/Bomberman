package test;

import gameplay.grid.Direction;
import gameplay.grid.Grid;
import gameplay.logic.Game;
import gameplay.logic.event.GameEvent;
import gameplay.logic.event.GameEventType;
import gameplay.logic.event.PlayerMovedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ControllerDriver {

    @Test
    public void playerIsAbleToMoveEastFromUpperWestCorner(){
        ArrayList<String> player = new ArrayList<>(List.of("Christoph"));

        Game game = new Game();
        game.initialize(player);
        game.initializeNextLevel();
        game.startLevel();

        game.movePlayer("Christoph", Direction.EAST);
        GameEvent polledEvent = null;
        PlayerMovedEvent expectedEvent = null;

        int counter = 0; // the player moved event should arise among the first 10 events
        while(expectedEvent == null && counter < 10){
            polledEvent = game.pollEventPump();
            if(polledEvent != null && polledEvent.getEventType() == GameEventType.PLAYER_MOVED) expectedEvent = (PlayerMovedEvent)polledEvent;
            counter++;
        }
        game.terminate();

        Assertions.assertNotEquals(null,expectedEvent);
        int distance = Grid.manhattanDistance(expectedEvent.getPreviousPosition(),expectedEvent.getNewPosition());
        Assertions.assertEquals(1,distance);
    }


}
