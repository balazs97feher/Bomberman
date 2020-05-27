package test;

import gameplay.LoggerMan;
import gameplay.grid.Direction;
import gameplay.grid.Grid;
import gameplay.logic.Game;
import gameplay.logic.event.BombPlacedEvent;
import gameplay.logic.event.GameEvent;
import gameplay.logic.event.GameEventType;
import gameplay.logic.event.PlayerMovedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ControllerDriver {
    @BeforeEach
    public void initializeLogging(){
        LoggerMan.initialize();
    }

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
            if(polledEvent != null){
                counter++;
                if(polledEvent.getEventType() == GameEventType.PLAYER_MOVED) expectedEvent = (PlayerMovedEvent)polledEvent;
            }
            polledEvent = null;
        }
        game.terminate();

        Assertions.assertNotEquals(null,expectedEvent);
        int distance = Grid.manhattanDistance(expectedEvent.getPreviousPosition(),expectedEvent.getNewPosition());
        Assertions.assertEquals(1,distance);
    }

    @Test
    public void PlayerIsAbleToPlaceBombAtStart(){
        ArrayList<String> player = new ArrayList<>(List.of("Christoph"));
        Game game = new Game();
        game.initialize(player);
        game.initializeNextLevel();
        game.startLevel();

        game.placeBomb("Christoph");
        GameEvent polledEvent = null;
        PlayerMovedEvent expectedMovedEvent = null;
        BombPlacedEvent expectedPlacedEvent = null;

        int counter = 0; // both events must occur among the first 10 events
        while((expectedMovedEvent == null || expectedPlacedEvent == null) && counter < 10){
            polledEvent = game.pollEventPump();
            if(polledEvent != null){
                counter++;
                if(polledEvent.getEventType() == GameEventType.PLAYER_MOVED) expectedMovedEvent = (PlayerMovedEvent)polledEvent;
                else if(polledEvent.getEventType() == GameEventType.BOMB_PLACED) expectedPlacedEvent = (BombPlacedEvent) polledEvent;
            }
            polledEvent = null;
        }
        game.terminate();

        Assertions.assertNotEquals(null,expectedMovedEvent);
        Assertions.assertNotEquals(null,expectedPlacedEvent);
        int distance = Grid.manhattanDistance(expectedMovedEvent.getPreviousPosition(),expectedMovedEvent.getNewPosition());
        Assertions.assertEquals(1,distance);

    }


}
