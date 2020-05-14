package gameplay.logic.event;

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

    public MonsterMovedEvent createMonsterMovedEvent(int monsterId, Position previousPos, Position nextPos){
        MonsterMovedEvent e = new MonsterMovedEvent(monsterId,previousPos,nextPos);
        gameEventLogger.log(Level.INFO,e.toString());
        return e;
    }

    public PlayerMovedEvent createPlayerMovedEvent(int playerId, Position previousPos, Position nextPos){
        PlayerMovedEvent e =  new PlayerMovedEvent(playerId,previousPos,nextPos);
        gameEventLogger.log(Level.INFO,e.toString());
        return e;
    }

}
