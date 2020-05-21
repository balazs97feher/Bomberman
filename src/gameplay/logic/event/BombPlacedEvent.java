package gameplay.logic.event;

import gameplay.grid.Position;

public class BombPlacedEvent {
    private Position position;

    public BombPlacedEvent(Position pos){
        position = pos;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "BombPlacedEvent{" +
            "position=" + position +
            '}';
    }
}
