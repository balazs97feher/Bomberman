package gameplay.logic.event;

import gameplay.grid.Direction;
import gameplay.grid.Position;

public class PlayerMovedEvent implements GameEvent {
    private int playerId;
    private Position previousPosition;
    private Position newPosition;
    private Direction direction;

    public PlayerMovedEvent(int id, Position previousPos, Position newPos, Direction dir){
        playerId = id;
        previousPosition = previousPos;
        newPosition = newPos;
        direction = dir;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.PLAYER_MOVED;
    }

    public int getPlayerId() {
        return playerId;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }

    public Position getNewPosition() {
        return newPosition;
    }

    @Override
    public String toString() {
        return "PlayerMovedEvent{" +
            "playerId=" + playerId +
            ", previousPosition=" + previousPosition.toString() +
            ", newPosition=" + newPosition.toString() +
            '}';
    }
}
