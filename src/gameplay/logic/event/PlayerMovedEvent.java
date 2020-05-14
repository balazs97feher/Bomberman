package gameplay.logic.event;

import gameplay.grid.Position;

public class PlayerMovedEvent implements GameEvent {
    private int playerId;
    private Position previousPosition;
    private Position newPosition;

    public PlayerMovedEvent(int id, Position previousPos, Position newPos){
        playerId = id;
        previousPosition = previousPos;
        newPosition = newPos;
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
