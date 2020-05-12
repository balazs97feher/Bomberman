package gameplay.logic.event;

import gameplay.LoggerMan;
import gameplay.grid.Position;

import java.util.logging.Level;

public class MonsterMovedEvent implements GameEvent{
    private int monsterId;
    private Position previousPosition;
    private Position newPosition;

    public MonsterMovedEvent(int id, Position previousPos, Position newPos){
        monsterId = id;
        previousPosition = previousPos;
        newPosition = newPos;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.MONSTER_MOVED;
    }

    public int getMonsterId() {
        return monsterId;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }

    public Position getNewPosition() {
        return newPosition;
    }

    @Override
    public String toString() {
        return "MonsterMovedEvent{" +
            "monsterId=" + monsterId +
            ", previousPosition=" + previousPosition.toString() +
            ", newPosition=" + newPosition.toString() +
            '}';
    }
}
