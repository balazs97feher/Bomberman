package gameplay.logic.event;

import gameplay.grid.Position;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * factory to create and log game events
 */
public class GameEventFactory {
    private Logger gameEventLogger;

    public GameEventFactory(){
        gameEventLogger = Logger.getLogger("GameEventLogger");

        try {
            FileHandler fileHandler = new FileHandler(gameEventLogger.getName());
            SimpleFormatter formatter = new SimpleFormatter();
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

}
