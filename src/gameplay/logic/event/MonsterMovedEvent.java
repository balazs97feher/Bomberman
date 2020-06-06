package gameplay.logic.event;

import gameplay.grid.Direction;
import gameplay.grid.Position;

public class MonsterMovedEvent implements GameEvent{
    private int monsterId;
    private Position previousPosition;
    private Position newPosition;
    private Direction direction;

    public MonsterMovedEvent(int id, Position previousPos, Position newPos, Direction dir){
        monsterId = id;
        previousPosition = previousPos;
        newPosition = newPos;
        direction = dir;
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

    public Direction getDirection() {
        return direction;
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
