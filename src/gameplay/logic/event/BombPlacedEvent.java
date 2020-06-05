package gameplay.logic.event;

import gameplay.grid.Position;

public class BombPlacedEvent implements GameEvent{
    private Position position;
    private int bombId;

    public BombPlacedEvent(Position pos, int id){
        position = pos;
        bombId = id;
    }

    public Position getPosition() {
        return position;
    }

    public int getBombId() {
        return bombId;
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
