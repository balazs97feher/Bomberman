package gameplay.logic.event;

import gameplay.grid.Position;

public class MonsterMovedEvent implements GameEvent{
    public int monsterId;
    public Position previousPosition;
    public Position newPosition;

    public MonsterMovedEvent(int id, Position previousPos, Position newPos){
        monsterId = id;
        previousPosition = previousPos;
        newPosition = newPos;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.MONSTER_MOVED;
    }
}
