package gameplay.logic.event;

import gameplay.grid.Direction;
import gameplay.grid.Position;

import java.io.IOException;
import java.util.logging.*;

/**
 * factory to create and log game events
 */
public class GameEventFactory {
    private Logger gameEventLogger;

    public GameEventFactory(){
        gameEventLogger = Logger.getLogger("GameEventLogger");

        try {
            FileHandler fileHandler = new FileHandler(gameEventLogger.getName());
//            SimpleFormatter formatter = new SimpleFormatter();
            XMLFormatter formatter = new XMLFormatter();
            fileHandler.setFormatter(formatter);
            gameEventLogger.addHandler(fileHandler);

            gameEventLogger.setUseParentHandlers(false);
            gameEventLogger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MonsterMovedEvent createMonsterMovedEvent(int monsterId, Position previousPos, Position nextPos, Direction direction){
        MonsterMovedEvent e = new MonsterMovedEvent(monsterId,previousPos,nextPos,direction);
        gameEventLogger.log(Level.INFO,e.toString());
        return e;
    }

    public PlayerMovedEvent createPlayerMovedEvent(int playerId, Position previousPos, Position nextPos, Direction direction){
        PlayerMovedEvent e =  new PlayerMovedEvent(playerId,previousPos,nextPos, direction);
        gameEventLogger.log(Level.INFO,e.toString());
        return e;
    }

    public BombPlacedEvent createBombPlacedEvent(Position position, int bombId){
        BombPlacedEvent e = new BombPlacedEvent(position, bombId);
        gameEventLogger.log(Level.INFO,e.toString());
        return e;
    }

    public BombDetonatedEvent createBombDetonatedEvent(int bombId){
        BombDetonatedEvent e = new BombDetonatedEvent(bombId);
        gameEventLogger.log(Level.INFO, e.toString());
        return e;
    }

    public PlayerKilledEvent createPlayerKilledEvent(int playerId){
        PlayerKilledEvent e = new PlayerKilledEvent(playerId);
        gameEventLogger.log(Level.INFO, e.toString());
        return e;
    }

    public MonsterKilledEvent createMonsterKilledEvent(int monsterId){
        MonsterKilledEvent e = new MonsterKilledEvent(monsterId);
        gameEventLogger.log(Level.INFO, e.toString());
        return e;
    }

}
