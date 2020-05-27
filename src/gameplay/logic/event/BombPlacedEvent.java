package gameplay.logic.event;

import gameplay.grid.Position;

public class BombPlacedEvent implements GameEvent{
    private Position position;

    public BombPlacedEvent(Position pos){
        position = pos;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.BOMB_PLACED;
    }

    @Override
    public String toString() {
        return "BombPlacedEvent{" +
            "position=" + position +
            '}';
    }
}
