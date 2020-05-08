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
    public EventType getEventType() {
        return EventType.MONSTER_MOVED;
    }
}
